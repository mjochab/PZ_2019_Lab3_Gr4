package com.app.library.controller.employee;

import com.app.library.model.Address;
import com.app.library.model.Book;
import com.app.library.model.BookUnit;
import com.app.library.model.Library;
import com.app.library.service.BookService;
import com.app.library.service.BookUnitService;
import com.app.library.service.LibraryService;
import com.app.library.service.PersistenceService;
import com.app.library.utils.AlertMessage;
import com.app.library.utils.BooksSignatureGeneratorUtils;
import com.app.library.utils.PersistenceKeys;
import com.app.library.utils.ViewUtils;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.ConstraintViolationException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class EditingBookController implements Initializable {

	private static final int DEFAULT_YEAR_OF_PUBLICATION = 2000;

	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private BookService bookService;

	@Autowired
	private LibraryService libraryService;

	@Autowired
	private ViewUtils viewUtils;

	@Autowired
	private ViewManager viewManager;

	@Autowired
	private BookUnitService bookUnitService;

	@Autowired
	private BooksSignatureGeneratorUtils booksSignatureGeneratorUtils;

	@FXML
	private TextField titleField;

	@FXML
	private TextField authorField;

	@FXML
	private TextField companyField;

	@FXML
	private Spinner<Integer> yearOfPublicationField;

	@FXML
	private ComboBox<Library> libraryField;

	private void initializeTextBoxes() {

		Book book = (Book) persistenceService.getStoredObject(PersistenceKeys.SINGLE_BOOK);
		titleField.setText(book.getName());
		authorField.setText(book.getAuthor());
		companyField.setText(book.getPublishingCompany());
		yearOfPublicationField.getValueFactory().setValue(book.getYearOfPublication());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		yearOfPublicationField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, DEFAULT_YEAR_OF_PUBLICATION));

		initializeTextBoxes();

		ObservableList<Library> libs = FXCollections.observableList(libraryService.findAll());
		ObservableList<Library> libraries = FXCollections.unmodifiableObservableList(libs);
		this.initializeComboBox(libraries);
	}

	@FXML
	private void saveChanges() {
		Book editedBook = (Book) persistenceService.getStoredObject(PersistenceKeys.SINGLE_BOOK);

		editedBook.setName(titleField.getText());
		editedBook.setAuthor(authorField.getText());
		editedBook.setPublishingCompany(companyField.getText());
		editedBook.setYearOfPublication(yearOfPublicationField.getValue());
		editedBook.setLibrary(libraryField.getValue());

		List<BookUnit> bookUnitList = bookUnitService.findByBookId(editedBook.getId());
		editedBook.setBookUnits(bookUnitList.stream().collect(Collectors.toSet()));

		if (titleField.getText().trim().isEmpty() || authorField.getText().trim().isEmpty() ||
				companyField.getText().trim().isEmpty() || yearOfPublicationField.getValue().toString().isEmpty()) {
			showErrorMessage("Nie wszystkie pola zostały wypełnione");
		} else {
			try {
				bookService.saveBook(editedBook);
				showSuccessMessage();
				booksSignatureGeneratorUtils.showWindowToSaveBookSignatures(editedBook);

			} catch (ConstraintViolationException ex) {
				showErrorMessage(ex.getMessage());
			}
		}

	}


	private void initializeComboBox(ObservableList<Library> libraries) {
		libraryField.setButtonCell(new EditingBookController.LibraryListCell());
		libraryField.setCellFactory((ListView<Library> p) -> {
			final ListCell<Library> cell = new EditingBookController.LibraryListCell();

			return cell;
		});
		libraryField.getItems().setAll(libraries);
		libraryField.getSelectionModel().selectFirst();
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

	private void showSuccessMessage() {
		AlertMessage message = new AlertMessage.Builder()
				.content("Dane zostały pomyślnie zedytowane")
				.header("Sukces")
				.build();

		viewUtils.showSuccessAlert(message);

	}

	private void showErrorMessage(String messageContent) {
		AlertMessage message = new AlertMessage.Builder()
				.content(messageContent)
				.header("Błąd")
				.build();

		viewUtils.showErrorAlert(message);
	}

	@FXML
	private void goToListOfBooks() {
		viewManager.show(ViewType.EMPLOYEE_LIST_OF_BOOKS);
	}

}
