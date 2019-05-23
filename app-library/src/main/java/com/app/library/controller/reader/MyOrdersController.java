package com.app.library.controller.reader;


import com.app.library.model.Book;
import com.app.library.model.BooksOrder;
import com.app.library.service.*;
import com.app.library.utils.PersistenceKeys;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


@Controller
public class MyOrdersController implements Initializable {

    @Autowired
    private ViewManager viewManager;

    @Autowired
    private PersistenceService persistenceService;

    @Autowired
    private BooksOrderService booksOrderService;

    @Autowired
    private BookUnitOrderService bookUnitOrderService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookUnitService bookUnitService;

    @FXML
    private TableView<List<String>> ordersTable = new TableView();

    @FXML
    private TableColumn<List<String>, String> idColumn, dateOfOrderColumn, statusColumn = new TableColumn();


    @FXML
    public void goToAccount(){
        viewManager.show(ViewType.READER_ACCOUNT);
    }

    @FXML
    public void goToHome(){
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    public void goToSearchBooks(){
        viewManager.show(ViewType.READER_SEARCH_BOOKS);
    }


    @FXML
    public void goToCart(){
        viewManager.show(ViewType.READER_CART);
    }


    //ustawianie itemów w tablicy
    private void setOrderTableItems(ObservableList orderTableItems){
        ordersTable.setItems(orderTableItems);
    }


    //tworzenie listy dla tabeli
    private ObservableList getListToTable(List<BooksOrder> booksOrders){
        ObservableList<List<String>> observableList = FXCollections.observableArrayList();
        for(int i = 0; i<booksOrders.size(); i++){
            List<String> stringList = new ArrayList<>();
            stringList.add(booksOrders.get(i).getId().toString());
            stringList.add(booksOrders.get(i).getCreatedAt().toString());
            if(booksOrders.get(i).isReleased()){
                stringList.add("Odebrano");
            }else{
                if(booksOrders.get(i).isReadyToRelease()){
                    stringList.add("Gotowe do odbioru");
                }else stringList.add("W trakcie realizacji");
            }

            observableList.addAll(stringList);
        }
        return observableList;
    }

    @FXML
    private void goToIndividualBooksView(){
        //przejście do widoku indywidualnego książki na onDoubleClick
        ordersTable.setRowFactory( tv -> {
            TableRow<List<String>> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2) {
                    List<String> table_row = row.getItem();
                    BooksOrder booksOrder = booksOrderService.findById(Integer.parseInt(table_row.get(0)));
                    persistenceService.setSelectedBooksOrder(booksOrder);
                    viewManager.show(ViewType.SINGLE_ORDER);
                }
            });
            return row;
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        dateOfOrderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));

        List<BooksOrder> booksOrders = booksOrderService.findByReaderId(persistenceService.getUser().getId());

        setOrderTableItems(getListToTable(booksOrders));

        goToIndividualBooksView();

    }
}

