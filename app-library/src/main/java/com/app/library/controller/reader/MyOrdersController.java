package com.app.library.controller.reader;


import com.app.library.model.Book;
import com.app.library.model.BookOrderUnit;
import com.app.library.model.BookUnit;
import com.app.library.model.BooksOrder;
import com.app.library.service.BookService;
import com.app.library.service.BookUnitOrderService;
import com.app.library.service.BookUnitService;
import com.app.library.service.BooksOrderService;
import com.app.library.view.ViewManager;

import com.app.library.view.ViewType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
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
    private TableColumn<List<String>, String> titleColumn, signatureColumn, dateOfOrderColumn, expireTimeColumn, statusColumn = new TableColumn();


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



    private void setOrderTableItems(ObservableList orderTableItems){
        ordersTable.setItems(orderTableItems);
    }



    private ObservableList getListToTable(List<BooksOrder> booksOrders){
        ObservableList<List<String>> observableList = FXCollections.observableArrayList();

        for(int i = 0; i<booksOrders.size(); i++){
            List<BookOrderUnit> bookOrderUnits = bookUnitOrderService.findByBooksOrderId(booksOrders.get(i).getId());
            for(int j = 0; j<bookOrderUnits.size(); j++){
                List<String> stringList = new ArrayList<>();

                BookOrderUnit bookOrderUnit = bookOrderUnits.get(j);
                BookUnit bookUnit = bookUnitService.findBySignature(bookOrderUnit.getBookUnit().getSignature());
                int bookId = bookUnit.getBook().getId();
                stringList.add(bookService.findById(bookId).getName());
                stringList.add(bookOrderUnits.get(j).getBookUnit().getSignature());
                //do wypełnienia
                stringList.add(null);
                stringList.add(null);
                stringList.add("w trakcie realizacji");

                observableList.addAll(stringList);

            }

        }

        return observableList;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        signatureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        dateOfOrderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        expireTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));

        //tutaj będzie findByUserId();
        List<BooksOrder> booksOrders = booksOrderService.findAllOrders();

        setOrderTableItems(getListToTable(booksOrders));

    }
}

