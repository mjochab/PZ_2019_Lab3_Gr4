package com.app.library.controller.employee;

import com.app.library.model.BookRental;
import com.app.library.model.BookUnit;
import com.app.library.service.BookRentalService;
import com.app.library.service.BookUnitService;
import com.app.library.service.PersistenceService;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ReturnOfBooksController implements Initializable {


	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private BookRentalService bookRentalService;

	@Autowired
	private BookUnitService bookUnitService;

	@Autowired
	private ViewManager viewManager;

	private BookRental selectedBookRental = new BookRental();

	@FXML
	private TableView<BookRental> rentalsTable;

	@FXML
	private TableColumn<BookRental, String> signatureColumn;

	@FXML
	private TableColumn<BookRental, String> statusColumn;

	@FXML
	private TableColumn<BookRental, LocalDate> dateOfOrderColumn;

	@FXML
	private TableColumn<BookRental, LocalDate> dateOfReturnColumn;

	@FXML
	private Button backButton, returnButton;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		returnButton.setDisable(true);

		signatureColumn.setCellValueFactory(this::getReaderNameProperty);
		statusColumn.setCellValueFactory(this::getStatusProperty);
		dateOfOrderColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfRental"));
		dateOfReturnColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfReturn"));

		findOrders();
		selectBookRental();
	}

	private SimpleStringProperty getReaderNameProperty(TableColumn.CellDataFeatures<BookRental, String> cellData) {
		return new SimpleStringProperty(cellData.getValue().getBookUnit().getSignature().toString());
	}

	private SimpleStringProperty getStatusProperty(TableColumn.CellDataFeatures<BookRental, String> cellData) {

		String readyToRelease = cellData.getValue().getBookUnit().isCheckedOut() == true ? "Wypożyczona" : "Oddana";

		return new SimpleStringProperty(readyToRelease);
	}

	private void selectBookRental() {
		rentalsTable.setRowFactory(tv -> {
			TableRow<BookRental> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				selectedBookRental = row.getItem();
					if (selectedBookRental.getBookUnit().isCheckedOut()) {
						returnButton.setDisable(false);
					} else {
						returnButton.setDisable(true);
					}

			});
			return row;
		});
	}

	@FXML
	private void returnBook() {
		BookUnit bookUnit = bookUnitService.findBySignature(selectedBookRental.getBookUnit().getSignature());
		bookUnit.setCheckedOut(false);
		bookUnitService.save(bookUnit);
		findOrders();
	}


	private void findOrders() {
		List<BookRental> booksOrders = bookRentalService.findAll();

		setTableItems(booksOrders);
	}


	private void setTableItems(List<BookRental> booksOrders) {
		rentalsTable.getItems().setAll(booksOrders);
	}


	@FXML
	public void goToOrders() {
		viewManager.show(ViewType.EMPLOYEE_READER_ORDERS);
	}
}
