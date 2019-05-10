package com.app.library.controller.shared;

import com.app.library.model.User;
import com.app.library.utils.ViewUtils;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

@Controller
public class RejestracjaController {

    @Autowired
    private ViewManager viewManager;

    @Autowired
    private ViewUtils viewUtils;

    @FXML
    private Button home;

    @FXML
    private TextField firstNameTextBox;

    @FXML
    private TextField surnameTextBox;

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
        String surname = surnameTextBox.getText();
        String email = mailTextBox.getText();
        String pesel = peselTextBox.getText();
        String code = codeTextBox.getText();
        String street = streetTextBox.getText();
        String city = cityTextBox.getText();
        String password = passwordTextBox.getText();
        String password2 = password2TextBox.getText();

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setSurname(surname);
        newUser.setEmail(email);
        newUser.setPesel(pesel);
//            newUser.setCode(code); // TODO set ADDRESS OBJECT!
//            newUser.setStreet(street);
//            newUser.setCity(city);
        newUser.setPassword(password);


        try {
//            bookService.saveUser(newUser); // TODO call registerReader !

            showSuccessMessage();
            resetFormFields();
        } catch (ConstraintViolationException ex) {
            showErrorMessage(ex.getMessage());
        }
    }

    private void showSuccessMessage() {
//        AlertMessage message = new AlertMessage();
//        message.setHeader("Sukces");
//        message.setContent("Dodano pomyślnie");
//        viewUtils.showSuccessAlert(message); // TODO
    }

    private void showErrorMessage(String messageContent) {
//        AlertMessage message = new AlertMessage();
//        message.setHeader("Błąd walidacji formularza"); // TODO
//        message.setContent(messageContent);
//
//        viewUtils.showErrorAlert(message);
    }

    private void resetFormFields() {
//        titleTextBox.clear();
//        authorTextBox.clear();
//        publishingCompanyTextBox.clear();
//        quantityNumberBox.getValueFactory().setValue(DEFAULT_BOOKS_QUANTITY);
//        yearOfPublicationNumberBox.getValueFactory().setValue(DEFAULT_YEAR_OF_PUBLICATION);
//        libraryComboBox.getSelectionModel().selectFirst(); // TODO
    }

    @FXML
    void addUser(MouseEvent event) {
        // TODO
    }

    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }

}
