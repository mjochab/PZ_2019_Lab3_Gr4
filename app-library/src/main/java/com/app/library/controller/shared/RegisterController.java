package com.app.library.controller.shared;

import com.app.library.model.Address;
import com.app.library.model.User;
import com.app.library.model.dto.ReaderRegistrationDto;
import com.app.library.service.UserService;
import com.app.library.utils.AlertMessage;
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
public class RegisterController {

    private UserService userService;

    @Autowired
    private ViewManager viewManager;

    @Autowired
    private ViewUtils viewUtils;

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
    private TextField confirmPasswordTextBox;

    @FXML
    void registerReader(MouseEvent event) {
        ReaderRegistrationDto registrationDto = getRegistrationDto();

        try {
            User newReader = userService.registerReaderAccount(registrationDto);

            showSuccessMessage(newReader.getFirstName());
            viewManager.show(ViewType.SIGN_IN);
        } catch (ConstraintViolationException ex) {
            showErrorMessage("Błąd walidacji formularza", ex.getMessage());
        } catch (Exception ex) {
            showErrorMessage("Błąd", ex.getMessage());
        }
    }

    private void showSuccessMessage(String username) {
        String contentMessage = String.format("%s, możesz się zalogować", username);

        AlertMessage message = new AlertMessage.Builder()
                .header("Pomyślna rejestracja")
                .content(contentMessage)
                .build();

        viewUtils.showSuccessAlert(message);
    }

    private void showErrorMessage(String title, String messageContent) {
        AlertMessage message = new AlertMessage.Builder()
                .header(title)
                .content(messageContent)
                .build();

        viewUtils.showErrorAlert(message);
    }

    private ReaderRegistrationDto getRegistrationDto() {
        Address address = new Address();
        address.setCity(cityTextBox.getText());
        address.setStreet(streetTextBox.getText());
        address.setZipCode(codeTextBox.getText());

        return new ReaderRegistrationDto.Builder()
                .setEmail(mailTextBox.getText())
                .setPesel(peselTextBox.getText())
                .setFirstName(firstNameTextBox.getText())
                .setSurname(surnameTextBox.getText())
                .setPassword(passwordTextBox.getText())
                .setConfirmPassword(confirmPasswordTextBox.getText())
                .setAddress(address)
                .build();
    }

    @FXML
    void goToLogin(ActionEvent event) {
        viewManager.show(ViewType.SIGN_IN);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
