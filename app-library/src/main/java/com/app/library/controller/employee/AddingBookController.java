package com.app.library.controller.employee;

import com.app.library.model.Address;
import com.app.library.model.Book;
import com.app.library.model.Library;
import com.app.library.service.BookService;
import com.app.library.service.LibraryService;
import com.app.library.utils.AlertMessage;
import com.app.library.utils.BooksSignatureGeneratorUtils;
import com.app.library.utils.ViewUtils;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.ConstraintViolationException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AddingBookController implements Initializable {

    private static final int DEFAULT_BOOKS_QUANTITY = 1;
    private static final int DEFAULT_YEAR_OF_PUBLICATION = 2000;

    private ViewManager viewManager;
    private BookService bookService;
    private LibraryService libraryService;
    private ViewUtils viewUtils;

    @Autowired
    private BooksSignatureGeneratorUtils booksSignatureGeneratorUtils;

    @FXML
    private TextField titleTextBox;

    @FXML
    private TextField authorTextBox;

    @FXML
    private TextField publishingCompanyTextBox;

    @FXML
    private Spinner<Integer> quantityNumberBox;

    @FXML
    private Spinner<Integer> yearOfPublicationNumberBox;

    @FXML
    private ComboBox<Library> libraryComboBox;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quantityNumberBox.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 200, DEFAULT_BOOKS_QUANTITY));
        yearOfPublicationNumberBox.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, DEFAULT_YEAR_OF_PUBLICATION));

        ObservableList<Library> libs = FXCollections.observableList(libraryService.findAll());
        ObservableList<Library> libraries = FXCollections.unmodifiableObservableList(libs);
        this.initializeComboBox(libraries);
    }

    private void initializeComboBox(ObservableList<Library> libraries) {
        libraryComboBox.setButtonCell(new LibraryListCell());
        libraryComboBox.setCellFactory((ListView<Library> p) -> {
            final ListCell<Library> cell = new LibraryListCell();

            return cell;
        });
        libraryComboBox.getItems().setAll(libraries);
        libraryComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void addBook(MouseEvent mouseEvent) {
        String title = titleTextBox.getText();
        String author = authorTextBox.getText();
        String publishingCompany = publishingCompanyTextBox.getText();
        Integer quantity = quantityNumberBox.getValue();
        Integer yearOfPublication = yearOfPublicationNumberBox.getValue();
        Library library = libraryComboBox.getValue();

        Book newBook = new Book.Builder()
                .name(title)
                .author(author)
                .publishingCompany(publishingCompany)
                .yearOfPublication(yearOfPublication)
                .quantity(quantity)
                .library(library)
                .build();

        try {
            Book savedBook = bookService.saveBook(newBook);

            showSuccessMessage(savedBook);
            resetFormFields();
        } catch (ConstraintViolationException ex) {
            showErrorMessage(ex.getMessage());
        }
    }

    private void showSuccessMessage(Book book) {
        AlertMessage message = new AlertMessage.Builder()
                .content("Książka została pomyślnie dodana")
                .header("Sukces")
                .build();

        viewUtils.showSuccessAlert(message);
        booksSignatureGeneratorUtils.showWindowToSaveBookSignatures(book);
    }

    private void showErrorMessage(String messageContent) {
        AlertMessage message = new AlertMessage.Builder()
                .content(messageContent)
                .header("Błąd walidacji formularza")
                .build();

        viewUtils.showErrorAlert(message);
    }

    private void resetFormFields() {
        titleTextBox.clear();
        authorTextBox.clear();
        publishingCompanyTextBox.clear();
        quantityNumberBox.getValueFactory().setValue(DEFAULT_BOOKS_QUANTITY);
        yearOfPublicationNumberBox.getValueFactory().setValue(DEFAULT_YEAR_OF_PUBLICATION);
        libraryComboBox.getSelectionModel().selectFirst();
    }

    private class LibraryListCell extends ListCell<Library> {
        @Override
        protected void updateItem(Library item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty && item != null) {
                Address libraryAddress = item.getAddress();
                String libraryCellText = String.format(
                        "%s, %s, %s, %s", item.getName(), libraryAddress.getCity(), libraryAddress.getStreet(), libraryAddress.getZipCode()
                );
                setText(libraryCellText);
            } else {
                setText(null);
            }
        }
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
    void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Autowired
    void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Autowired
    void setViewUtils(ViewUtils viewUtils) {
        this.viewUtils = viewUtils;
    }
}
