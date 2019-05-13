package com.app.library.controller.boss;

import com.app.library.controller.employee.AddingBookController;
import com.app.library.model.Address;
import com.app.library.model.EmployeeOfLibrary;
import com.app.library.model.Library;
import com.app.library.model.User;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

    @Autowired
    private ViewManager viewManager;

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

    @Autowired
    void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
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
        viewManager.show(ViewType.BOSS_LIST_OF_LIBRERIES);
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
//        user.setRole(3);
        user.setFirstName(employeeFirstName.getText());
        user.setSurname(employeeSurName.getText());
        user.setPesel(employeePesel.getText());
        user.setEmail(employeeEmail.getText());
        user.setPassword(employeePassword.getText());
        user.setAddress(userAddress);

        EmployeeOfLibrary employee = new EmployeeOfLibrary();
//        employeeOfLibraryService.save(employee);
    }

}

