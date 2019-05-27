package com.app.library.controller.reader;

import com.app.library.model.Address;
import com.app.library.model.User;
import com.app.library.service.PersistenceService;
import com.app.library.service.UserService;
import com.app.library.utils.AlertMessage;
import com.app.library.utils.PersistenceKeys;
import com.app.library.utils.ViewUtils;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolationException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AccountController implements Initializable {

	@Autowired
	private ViewManager viewManager;

	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private UserService userService;

	@Autowired
	private ViewUtils viewUtils;

	@FXML
	private TextField nameTextBox, surnameTextBox, peselTextBox, mailTextBox, cityTextBox, streetTextBox, codeTextBox;

	@FXML
	public void goToSearchBooks() {
		viewManager.show(ViewType.READER_SEARCH_BOOKS);
	}

	@FXML
	public void goToMyOrders() {
		viewManager.show(ViewType.READER_MY_ORDERS);
	}

	@FXML
	public void goToHome() {
		viewManager.show(ViewType.MAIN);
	}

	@FXML
	public void goToCart() {
		viewManager.show(ViewType.READER_CART);
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillTextField();
	}


	public void fillTextField() {
		User user = (User) persistenceService.getStoredObject(PersistenceKeys.LOGGED_READER);

		nameTextBox.setText(user.getFirstName());
		surnameTextBox.setText(user.getSurname());
		peselTextBox.setText(user.getPesel());
		mailTextBox.setText(user.getEmail());
		cityTextBox.setText(user.getAddress().getCity());
		streetTextBox.setText(user.getAddress().getStreet());
		codeTextBox.setText(user.getAddress().getZipCode());
	}

	@FXML
	public void updateProfile() {
		User user = (User) persistenceService.getStoredObject(PersistenceKeys.LOGGED_READER);

		if (!isFormValid()) {

			showErrorMessage("Wypełnij wszystkie pola.");

		} else {

			user.setFirstName(nameTextBox.getText());
			user.setSurname(surnameTextBox.getText());
			user.setPesel(peselTextBox.getText());
			user.setEmail(mailTextBox.getText());
			Address address = new Address();
			address.setCity(cityTextBox.getText());
			address.setStreet(streetTextBox.getText());
			address.setZipCode(codeTextBox.getText());
			user.setAddress(address);

			try {
				userService.save(user);
				showSuccessMessage("Dokonano zmiany.");
			} catch (ConstraintViolationException ex) {
				showErrorMessage(ex.getMessage());
			}
		}
	}

	private boolean isFormValid() {
		String name = nameTextBox.getText();
		String surname = surnameTextBox.getText();
		String pesel = peselTextBox.getText();
		String mail = mailTextBox.getText();
		String city = cityTextBox.getText();
		String street = streetTextBox.getText();
		String code = codeTextBox.getText();

		return !(StringUtils.isEmpty(name) || StringUtils.isEmpty(surname) || StringUtils.isEmpty(pesel) ||
				StringUtils.isEmpty(mail) || StringUtils.isEmpty(city) || StringUtils.isEmpty(street) || StringUtils.isEmpty(code));
	}

	private void showSuccessMessage(String content) {
		AlertMessage message = new AlertMessage.Builder()
				.content(content)
				.header("Edycja")
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

}
