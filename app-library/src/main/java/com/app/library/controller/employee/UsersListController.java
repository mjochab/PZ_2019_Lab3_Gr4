package com.app.library.controller.employee;

import com.app.library.model.User;
import com.app.library.service.UserService;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class UsersListController implements Initializable {

    private ViewManager viewManager;
    private UserService userService;

    @FXML
    private TableView<User> readersTable;

    @FXML
    private TableColumn<User, String> firstNameColumn;

    @FXML
    private TableColumn<User, String> surnameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> peselColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<>("pesel"));

        List<User> readers = userService.findAllReaders();

        setTableItems(readers);
    }

    @FXML
    void findReaders(KeyEvent event) {
        String query = ((TextField) event.getTarget()).getText();
        List<User> readers = userService.findReadersByQueryIgnoreCase(query);

        setTableItems(readers);
    }

    private void setTableItems(List<User> readers) {
        readersTable.getItems().setAll(readers);
    }

    @FXML
    public void goToEmployeeAccount() {
        viewManager.show(ViewType.EMPLOYEE_ACCOUNT);
    }

    @FXML
    public void goToListOfBooks() {
        viewManager.show(ViewType.EMPLOYEE_LIST_OF_BOOKS);
    }

    @FXML
    public void goToReaderOrders() {
        viewManager.show(ViewType.EMPLOYEE_READER_ORDERS);
    }

    @FXML
    public void goToListOfUsers() {
        viewManager.show(ViewType.EMPLOYEE_LIST_OF_USERS);
    }

    @FXML
    public void goToHome() {
        viewManager.show(ViewType.MAIN);
    }

    @Autowired
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}