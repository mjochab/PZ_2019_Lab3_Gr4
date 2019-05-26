package com.app.library.controller.reader;

import com.app.library.model.Book;
import com.app.library.model.BookOrderUnit;
import com.app.library.model.BookRental;
import com.app.library.service.*;
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

import javax.validation.constraints.NotNull;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class IndividualOrderController implements Initializable {

    @Autowired
    private PersistenceService persistenceService;

    @Autowired
    private ViewManager viewManager;

    @Autowired
    private BookUnitOrderService bookUnitOrderService;

    @Autowired
    private BookUnitService bookUnitService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRentalService bookRentalService;

    @FXML
    private TableView<List<String>> bookUnitTable = new TableView();

    @FXML
    private TableColumn<List<String>, String> signatureColumn, titleColumn, dateOfRentalColumn, expireDateColumn, statusColumn = new TableColumn<>();

    @FXML
    private void goToMyOrders(){
        viewManager.show(ViewType.READER_MY_ORDERS);
    }

    private void setTableItems(ObservableList tableItems){
        bookUnitTable.setItems(tableItems);
    }



    private ObservableList getListForTable(@NotNull List<BookOrderUnit> bookOrderUnits){
        ObservableList<List<String>> observableList = FXCollections.observableArrayList();
        Book book = new Book();
        for(int i = 0; i<bookOrderUnits.size(); i++){
            List<String> stringList = new ArrayList<>();
            stringList.add(bookOrderUnits.get(i).getBookUnit().getSignature().toString());

            book.setName(bookOrderUnits.get(i).getBookUnit().getBook().getName());
            stringList.add(book.getName());



            if(!bookRentalService.findByBookOrderUnitId(bookOrderUnits.get(i).getId()).isEmpty()){
                stringList.add(bookRentalService.findByBookOrderUnitId(bookOrderUnits.get(i).getId()).get(i).getDateOfRental().toString());
                stringList.add(bookRentalService.findByBookOrderUnitId(bookOrderUnits.get(i).getId()).get(i).getDateOfReturn().toString());
            }else {
                stringList.add(" ");
                stringList.add(" ");
            }

            //sprawdzenie czy książka została skompletowana
            if(!bookOrderUnits.get(i).isReadyToRent()){
                stringList.add("W trakcie kompletowania");
            } else{
                stringList.add("Gotowa");
            }

            observableList.addAll(stringList);
        }


        return observableList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        signatureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        dateOfRentalColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        expireDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));

        List<BookOrderUnit> bookOrderUnits = bookUnitOrderService.findByBooksOrderId(persistenceService.getSelectedBooksOrder().getId());

        setTableItems(getListForTable(bookOrderUnits));

    }
}
