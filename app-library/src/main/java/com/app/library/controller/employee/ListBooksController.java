package com.app.library.controller.employee;

import com.app.library.model.Book;
import com.app.library.service.BookService;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ListBooksController implements Initializable {

    private ViewManager viewManager;
    private BookService bookService;

    @FXML
    private TableView<Book> booksTable;

    @FXML
    private TableColumn<Book, String> publishingCompanyColumn;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, Number> quantityColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        publishingCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("publishingCompany"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        quantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getBookUnits().size()));

        List<Book> books = bookService.findAll();

        setTableItems(books);
    }

    @FXML
    public void findBooks(KeyEvent event) {
        String query = ((TextField) event.getTarget()).getText();
        List<Book> books = bookService.findByQueryIgnoreCase(query);

        setTableItems(books);
    }

    private void setTableItems(List<Book> books) {
        ObservableList<Book> observableBooks = FXCollections.observableList(books);
        booksTable.setItems(observableBooks);
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
    public void goToRealizedOrders() {
        viewManager.show(ViewType.EMPLOYEE_REALIZED_ORDERS);
    }

    @FXML
    public void goToListOfUsers() {
        viewManager.show(ViewType.EMPLOYEE_LIST_OF_USERS);
    }

    @FXML
    public void goToHome() {
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    public void goToAddBook() {
        viewManager.show(ViewType.EMPLOYEE_ADD_OF_BOOKS);
    }

    @Autowired
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}
