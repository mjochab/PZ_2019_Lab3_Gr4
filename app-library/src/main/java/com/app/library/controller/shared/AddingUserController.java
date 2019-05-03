package com.app.library.controller.shared;


import com.app.library.model.Library;
import com.app.library.model.User;
import com.app.library.service.BookService;
import com.app.library.service.LibraryService;
import com.app.library.utils.AlertMessage;
import com.app.library.utils.ViewUtils;
import com.app.library.view.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

public class RejestracjaController {

    @Autowired
    private ViewManager viewManager;

    @Autowired
    private BookService bookService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private ViewUtils viewUtils;

    @FXML
    private Button home;

    @FXML
    private TextField firstNameTextBox;

    @FXML
    private TextField secondNameTextBox;

    @FXML
    private TextField mailTextBox;

    @FXML
    private TextField peselTextBox;

    @FXML
    private TextField codeTextBox;

    @FXML
    private TextField streetTextBox;

    @FXML
    private TextField cityTextBox;

    @FXML
    private TextField passwordTextBox;

    @FXML
    private TextField password2TextBox;

    @FXML
    public void addBook(MouseEvent mouseEvent) {
        String firstName = firstNameTextBox.getText();
        String secondName = secondNameTextBox.getText();
        String mail = mailTextBox.getText();
        String pesel = peselTextBox.getText();
        String code = codeTextBox.getText();
        String street = streetTextBox.getText();
        String city = cityTextBox.getText();
        String password = passwordTextBox.getText();
        String password2 = password2TextBox.getText();

        User newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setSecondName(secondName);
            newUser.setMail(mail);
            newUser.setPesel(pesel);
            newUser.setCode(code);
            newUser.setStreet(street);
            newUser.setCity(city);
            newUser.setPassword(password);


        try {
            bookService.saveBook(newUser);

            showSuccessMessage();
            resetFormFields();
        } catch (ConstraintViolationException ex) {
            showErrorMessage(ex.getMessage());
        }
    }

    private void showSuccessMessage() {
        AlertMessage message = new AlertMessage();
        message.setHeader("Sukces");
        message.setContent("Dodano pomyślnie");
        viewUtils.showSuccessAlert(message);
    }

    private void showErrorMessage(String messageContent) {
        AlertMessage message = new AlertMessage();
        message.setHeader("Błąd walidacji formularza");
        message.setContent(messageContent);

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

    @FXML
    void goToHome(ActionEvent event) {

    }

}
