package com.app.library.controller.reader;

import com.app.library.model.Book;
import com.app.library.service.BookService;
import com.app.library.service.PersistenceService;
import com.app.library.utils.PersistenceKeys;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class SearchBooksController implements Initializable {


    @Autowired
    private ViewManager viewManager;

    @Autowired
    private BookService bookService;

    @Autowired
    private PersistenceService persistanceService;

    @FXML
    private TableView table = new TableView();

    @FXML
    private TableColumn colTitle = new TableColumn();

    @FXML
    private TableColumn colAuthor = new TableColumn();

    @FXML
    private TableColumn colPublishingCompany = new TableColumn();

    @FXML
    private TableColumn colYearOfPublication = new TableColumn();

    @FXML
    private TableColumn colAvailable = new TableColumn();


    @FXML
    public void goToAccount() {
        viewManager.show(ViewType.READER_ACCOUNT);
    }

    @FXML
    public void goToHome() {
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    public void goToOrders() {
        viewManager.show(ViewType.READER_MY_ORDERS);
    }

    @FXML
    public void goToCart() {
        viewManager.show(ViewType.READER_CART);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colYearOfPublication.setCellValueFactory(new PropertyValueFactory<>("yearOfPublication"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colPublishingCompany.setCellValueFactory(new PropertyValueFactory<>("publishingCompany"));

        List<Book> books = bookService.findAll();
        ObservableList observableBooks = FXCollections.observableArrayList(books);
        table.setItems(observableBooks);

        table.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked((event) -> {
                if (event.getClickCount() == 2) {
                    Book book = row.getItem();
                    persistanceService.addToStore(PersistenceKeys.SINGLE_BOOK, book);
                    viewManager.show(ViewType.SINGLE_BOOK);
                }
            });
            return row;
        });
    }
}



