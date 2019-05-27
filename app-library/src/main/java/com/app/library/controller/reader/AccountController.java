package com.app.library.controller.reader;

import com.app.library.model.Address;
import com.app.library.model.User;
import com.app.library.service.PersistenceService;
import com.app.library.service.UserService;
import com.app.library.utils.AlertMessage;
import com.app.library.utils.ViewUtils;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
    private TextField nameTextBox, surnameTextBox, peselTextBox, mailTextBox, cityTextBox, streetTextBox,
            codeTextBox = new TextField();



    @FXML
    public void goToSearchBooks() {
        viewManager.show(ViewType.READER_SEARCH_BOOKS);
    }

    /**
     * Metoda, dzięki której przechodzimy do widoku zamówień czytelnika.
     */
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

        User user = (User) persistenceService.getStoredObject("LOGGED_READER");

        nameTextBox.setText(user.getFirstName());
        surnameTextBox.setText(user.getSurname());
        peselTextBox.setText(user.getPesel());
        mailTextBox.setText(user.getEmail());
        cityTextBox.setText(user.getAddress().getCity());
        streetTextBox.setText(user.getAddress().getStreet());
        codeTextBox.setText(user.getAddress().getZipCode());

    }

    @FXML
    public void profilUpdate(){

        User user = (User) persistenceService.getStoredObject("LOGGED_READER");





        String name = nameTextBox.getText();
        String surname = surnameTextBox.getText();
        String pesel =  peselTextBox.getText();
        String mail =  mailTextBox.getText();
        String city =  cityTextBox.getText();
        String street =  streetTextBox.getText();
        String code =  codeTextBox.getText();

        if(name.equals(null) || surname.equals(null) || pesel.equals(null) || mail.equals(null) || city.equals(null)
                || street.equals(null) || code.equals(null) ) {

            showErrorMessage("Wypełnij wszystkie pola.");

        }else {

            user.setFirstName(name);
            user.setSurname(surname);
            user.setPesel(pesel);
            user.setEmail(mail);
            Address address = new Address();
            address.setCity(city);
            address.setStreet(street);
            address.setZipCode(code);
            user.setAddress(address);

            userService.saveUser(user);

            showSuccessMessage("Dokonano zmiany.");
        }


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
