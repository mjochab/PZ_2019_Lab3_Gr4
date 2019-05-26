package com.app.library.controller.employee;

import com.app.library.model.BookOrderUnit;
import com.app.library.model.BookRental;
import com.app.library.model.BooksOrder;
import com.app.library.model.User;
import com.app.library.service.BookRentalService;
import com.app.library.service.BookUnitOrderService;
import com.app.library.service.BooksOrderService;
import com.app.library.service.PersistenceService;
import com.app.library.utils.DateUtils;
import com.app.library.utils.PersistenceKeys;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class EmployeeSingleOrderController implements Initializable {

	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private BookUnitOrderService bookUnitOrderService;

	@Autowired
	private BookRentalService bookRentalService;

	@Autowired
	private ViewManager viewManager;

	@Autowired
	private BooksOrderService booksOrderService;


	@FXML
	private TableView<BookOrderUnit> orderTable = new TableView<>();

	@FXML
	private TableColumn<BookOrderUnit, String> signatureColumn = new TableColumn<>(), titleColumn = new TableColumn<>();

	@FXML
	private Button applyButton = new Button(), releaseButton = new Button();

	@FXML
	public void goBack() {
		viewManager.show(ViewType.EMPLOYEE_READER_ORDERS);
	}

	@FXML
	public void realizeOrder() {
		BooksOrder order = persistenceService.getSelectedBooksOrder();
		List<BookOrderUnit> bookOrderUnits = bookUnitOrderService.findByBooksOrderId(order.getId());

		LocalDate now = LocalDate.now();
		Date dateOfRental = DateUtils.asDate(now);
		Date dateOfReturn = getDateOfReturn(now);

		User borrower = (User) persistenceService.getStoredObject(PersistenceKeys.LOGGED_EMPLOYEE);

		List<BookRental> bookRentals = bookOrderUnits.stream()
				.map(bookOrderUnit -> {
					BookRental bookRental = new BookRental();

					bookRental.setDateOfRental(dateOfRental);
					bookRental.setDateOfReturn(dateOfReturn);

					bookRental.setBorrower(borrower);
					bookRental.setLender(order.getReader());
					bookRental.setBookOrderUnit(bookOrderUnit);
					bookRental.setBookUnit(bookOrderUnit.getBookUnit());

					return bookRental;
				})
				.collect(Collectors.toList());

		bookRentalService.saveAll(bookRentals);

		bookOrderUnits.forEach(
				orderUnit -> {
					orderUnit.setReadyToRent(true);
				}
		);
		bookUnitOrderService.saveAll(bookOrderUnits);
		order.setReadyToRelease(true);
		booksOrderService.save(order);

		setDisableButtonIfRealized(bookOrderUnits);
	}

	private Date getDateOfReturn(LocalDate date) {
		LocalDate futureDate = date.plusMonths(1);

		return DateUtils.asDate(futureDate);
	}

	private void setDisableButtonIfRealized(List<BookOrderUnit> bookOrderUnits) {
		if (!bookRentalService.findByBookOrderUnitId(bookOrderUnits.get(0).getId()).isEmpty()) {
			applyButton.setDisable(true);
			releaseButton.setDisable(false);
		}
		disabelButtonIfReleased();
	}

	private void disabelButtonIfReleased() {
		if (persistenceService.getSelectedBooksOrder().isReleased()) {
			releaseButton.setDisable(true);
		}
	}


	@FXML
	private void releaseOrder() {
		BooksOrder booksOrder = persistenceService.getSelectedBooksOrder();
		booksOrder.setReleased(true);
		booksOrderService.save(booksOrder);
		releaseButton.setDisable(true);

	}


	private void fillTable(List<BookOrderUnit> bookOrderUnits) {
		ObservableList<BookOrderUnit> obsUnits = FXCollections.observableList(bookOrderUnits);
		orderTable.setItems(obsUnits);
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		releaseButton.setDisable(true);

		signatureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBookUnit().getSignature().toString()));
		titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBookUnit().getBook().getName()));

		List<BookOrderUnit> bookOrderUnits = bookUnitOrderService.findByBooksOrderId(persistenceService.getSelectedBooksOrder().getId());
		fillTable(bookOrderUnits);

		setDisableButtonIfRealized(bookOrderUnits);
	}
}
