package com.app.library.controller.boss;

import com.app.library.model.Address;
import com.app.library.model.EmployeeOfLibrary;
import com.app.library.model.Library;
import com.app.library.model.User;
import com.app.library.service.EmployeeOfLibraryService;
import com.app.library.service.UserService;
import com.app.library.utils.AlertMessage;
import com.app.library.utils.ViewUtils;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.app.library.service.LibraryService;


@Controller
public class AddEmployeeController implements Initializable {

    private LibraryService libraryService;
    private UserService userService;
    private EmployeeOfLibraryService employeeOfLibraryService;

    @Autowired
    private ViewManager viewManager;

    @Autowired
    private ViewUtils viewUtils;

    @FXML
    private TextField employeeFirstName;

    @FXML
    private TextField employeeSurName;

    @FXML
    private TextField employeePesel;

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
    private TextField employeeEmail;

    @FXML
    private TextField employeePassword;

    @FXML
    private ComboBox<Library> libraryComboBox;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Library> libs = FXCollections.observableList(libraryService.findAll());
        ObservableList<Library> libraries = FXCollections.unmodifiableObservableList(libs);
        this.initializeComboBox(libraries);
    }

    private void initializeComboBox(ObservableList<Library> libraries) {
        libraryComboBox.setButtonCell(new AddEmployeeController.LibraryListCell());
        libraryComboBox.setCellFactory((ListView<Library> p) -> {
            final ListCell<Library> cell = new AddEmployeeController.LibraryListCell();
            return cell;
        });
        libraryComboBox.getItems().setAll(libraries);
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



    @FXML
    public void addEmployee(MouseEvent mouseEvent) {
        Address userAddress = new Address();
        userAddress.setZipCode(zipCode.getText());
        userAddress.setCity(town.getText());
        userAddress.setStreet(street.getText());
        userAddress.setState(state.getText());
        userAddress.setCountry(country.getText());

        User user = new User();
        user.setRole(userService.getEmployeeRole());
        user.setFirstName(employeeFirstName.getText());
        user.setSurname(employeeSurName.getText());
        user.setPesel(employeePesel.getText());
        user.setEmail(employeeEmail.getText());
        String encryptedPassword = userService.getEncryptedPassword(employeePassword.getText());
        user.setPassword(encryptedPassword);
        user.setAddress(userAddress);

        EmployeeOfLibrary employee = new EmployeeOfLibrary();
        employee.setLibrary(libraryComboBox.getValue());
        employee.setUser(user);

        try {
            employeeOfLibraryService.save(employee);
            showSuccessMessage();
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }

    private void showSuccessMessage() {
        AlertMessage message = new AlertMessage.Builder()
                .content("Pracownik został pomyślnie dodany")
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

    @Autowired
    void setEmployeeOfLibraryService(EmployeeOfLibraryService employeeOfLibraryService) {
        this.employeeOfLibraryService = employeeOfLibraryService;
    }

    @Autowired
    void setUserService(UserService userService) { this.userService = userService; }

    @Autowired
    void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
}

