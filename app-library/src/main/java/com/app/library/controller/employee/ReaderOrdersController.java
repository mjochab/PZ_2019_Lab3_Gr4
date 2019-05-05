package com.app.library.controller.employee;

import com.app.library.model.Book;
import com.app.library.model.BooksOrder;
import com.app.library.service.BooksOrderService;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
        readerNameColumn.setCellValueFactory(this::getReaderNameColumnProperty);
        readyToReleaseColumn.setCellValueFactory(this::getReadyToReleaseColumnProperty);
        quantityOfBooksColumn.setCellValueFactory(this::getQuantityOfBooksColumnProperty);
        orderCreatedAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        orderDateFromDatePicker.setValue(orderDateFrom);
        findOrders();
    }

    private SimpleStringProperty getReaderNameColumnProperty(TableColumn.CellDataFeatures<BooksOrder, String> cellData) {
        return new SimpleStringProperty(cellData.getValue().getReader().getFullName());
    }

    private SimpleStringProperty getReadyToReleaseColumnProperty(TableColumn.CellDataFeatures<BooksOrder, String> cellData) {
        String readyToRelease = cellData.getValue().isReadyToRelease() == true ? "TAK" : "NIE";

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
        List<BooksOrder> booksOrders = booksOrderService.findByQueryIgnoreCaseAndCreatedAtBetween(searchQuery, orderDateFrom, orderDateTo);

        setTableItems(booksOrders);
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