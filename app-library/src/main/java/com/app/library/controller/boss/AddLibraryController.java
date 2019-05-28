package com.app.library.controller.boss;

import com.app.library.model.Address;
import com.app.library.model.Library;
import com.app.library.model.User;
import com.app.library.service.LibraryService;
import com.app.library.service.PersistenceService;
import com.app.library.utils.AlertMessage;
import com.app.library.utils.PersistenceKeys;
import com.app.library.utils.ViewUtils;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;


@Controller
public class AddLibraryController {
    @Autowired
    private LibraryService libraryService;

    @Autowired
    private ViewUtils viewUtils;

    @Autowired
    private ViewManager viewManager;

    @Autowired
    private PersistenceService persistenceService;

    @FXML
    private TextField libraryName;

    @FXML
    private TextField zipCode;

    @FXML
    private TextField town;

    @FXML
    private TextField country;

    @FXML
    private TextField state;

    @FXML
    private TextField street;

    @FXML
    public void addLibrary() {
        User user = (User) persistenceService.getStoredObject(PersistenceKeys.LOGGED_DIRECTOR);
        Address libraryAddress = new Address();
        libraryAddress.setZipCode(zipCode.getText());
        libraryAddress.setCity(town.getText());
        libraryAddress.setStreet(street.getText());
        libraryAddress.setState(state.getText());
        libraryAddress.setCountry(country.getText());

        Library library = new Library();
        library.setAddress(libraryAddress);
        library.setName(libraryName.getText());
        library.setDirector(user);

        try {
            libraryService.save(library);
            showSuccessMessage();
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }

    private void showSuccessMessage() {
        AlertMessage message = new AlertMessage.Builder()
                .content("Biblioteka została pomyślnie dodana")
                .header("Sukces")
                .build();

        viewUtils.showSuccessAlert(message);
    }
    private void showErrorMessage(String messageContent) {
        AlertMessage message = new AlertMessage.Builder()
                .content(messageContent)
                .header("Błąd walidacji formularza")
                .build();

        viewUtils.showErrorAlert(message);
    }




    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    public void goToListEmployee(ActionEvent event) throws IOException {
        viewManager.show(ViewType.BOSS_LIST_OF_EMPLOYEE);
    }

    @FXML
    public void goToListLibraries(ActionEvent event) throws IOException {
        viewManager.show(ViewType.BOSS_LIST_OF_LIBRARIES);
    }

    @FXML
    public void goToAddEmployee(ActionEvent event) throws IOException {
        viewManager.show(ViewType.BOSS_ADD_EMPLOYEE);
    }

    @FXML
    public void goToAccount(ActionEvent event) throws IOException {
        viewManager.show(ViewType.BOSS_ACCOUNT);
    }

    @FXML
    public void goToLogout(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }
}
