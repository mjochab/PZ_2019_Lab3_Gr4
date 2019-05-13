package com.app.library.controller.employee;

import com.app.library.model.Book;
import com.app.library.model.BooksOrder;
import com.app.library.service.BooksOrderService;
import com.app.library.service.PersistenceService;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ReaderOrdersController implements Initializable {

    private ViewManager viewManager;
    private BooksOrderService booksOrderService;

    private String searchQuery = "";
    private LocalDate orderDateFrom = LocalDate.now();
    private LocalDate orderDateTo = null;

    @Autowired
    private PersistenceService persistenceService;


    @FXML
    private TableView<BooksOrder> readerOrdersTable;

    @FXML
    private TableColumn<BooksOrder, String> readerNameColumn;

    @FXML
    private TableColumn<BooksOrder, String> readyToReleaseColumn;

    @FXML
    private TableColumn<BooksOrder, Number> quantityOfBooksColumn;

    @FXML
    private TableColumn<BooksOrder, LocalDate> orderCreatedAtColumn;

    @FXML
    private DatePicker orderDateFromDatePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readerNameColumn.setCellValueFactory(this::getIdColumnProperty);
        readyToReleaseColumn.setCellValueFactory(this::getReadyToReleaseColumnProperty);
        quantityOfBooksColumn.setCellValueFactory(this::getQuantityOfBooksColumnProperty);
        orderCreatedAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        orderDateFromDatePicker.setValue(orderDateFrom);
        findOrders();
        onOrderDoubleClick();
    }

    private SimpleStringProperty getIdColumnProperty(TableColumn.CellDataFeatures<BooksOrder, String> cellData) {
        return new SimpleStringProperty(cellData.getValue().getId().toString());
    }

    private SimpleStringProperty getReadyToReleaseColumnProperty(TableColumn.CellDataFeatures<BooksOrder, String> cellData) {
        String readyToRelease = cellData.getValue().isReadyToRelease() == true ? "Gotowe" : "W trakcie kompletowania";

        return new SimpleStringProperty(readyToRelease);
    }

    private SimpleIntegerProperty getQuantityOfBooksColumnProperty(TableColumn.CellDataFeatures<BooksOrder, Number> cellData) {
        int ordersUnitsSize = cellData.getValue().getBookOrderUnits().size();

        return new SimpleIntegerProperty(ordersUnitsSize);
    }

    @FXML
    private void handleSearchOrdersTextChanged(KeyEvent event) {
        this.searchQuery = ((TextField) event.getTarget()).getText();
        findOrders();
    }

    @FXML
    private void handleOrderDateFromChanged(ActionEvent event) {
        this.orderDateFrom = ((DatePicker) event.getTarget()).getValue();
        findOrders();
    }

    @FXML
    private void handleOrderDateToChanged(ActionEvent event) {
        this.orderDateTo = ((DatePicker) event.getTarget()).getValue();
        findOrders();
    }

    private void findOrders() {
        List<BooksOrder> booksOrders = booksOrderService.findAllOrders();

        setTableItems(booksOrders);
    }

    private void onOrderDoubleClick(){
        readerOrdersTable.setRowFactory( tv -> {
            TableRow<BooksOrder> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2){
                    persistenceService.setSelectedBooksOrder(row.getItem());
                    viewManager.show(ViewType.EMPLOYEE_SINGLE_ORDER);
                }

            });
            return row;
        });
    }

    private void setTableItems(List<BooksOrder> booksOrders) {
        readerOrdersTable.getItems().setAll(booksOrders);
    }

    @FXML
    public void goToEmployeeAccount() {
        viewManager.show(ViewType.EMPLOYEE_ACCOUNT);
    }

    @FXML
    public void goToListOfBooks() {
        viewManager.show(ViewType.EMPLOYEE_LIST_OF_BOOKS);
    }

    @FXML
    public void goToReaderOrders() {
        viewManager.show(ViewType.EMPLOYEE_READER_ORDERS);
    }

    @FXML
    public void goToListOfUsers() {
        viewManager.show(ViewType.EMPLOYEE_LIST_OF_USERS);
    }

    @FXML
    public void goToHome() {
        viewManager.show(ViewType.MAIN);
    }

    @Autowired
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Autowired
    public void setBooksOrderService(BooksOrderService booksOrderService) {
        this.booksOrderService = booksOrderService;
    }
}