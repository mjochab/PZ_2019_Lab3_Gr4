package com.app.library.controller.employee;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class AccountEmployeeController {

    @Autowired
    private ViewManager viewManager;

    @FXML
    public void goToListBooks(Event event) throws IOException {
        viewManager.show(ViewType.EMPLOYEE_LIST_OF_BOOKS);
    }


    @FXML
    public void goToListOrders(Event event) throws IOException {
        viewManager.show(ViewType.EMPLOYEE_REALIZED_ORDERS);
    }

    @FXML
    public void goToAddBooks(Event event) throws IOException {
        viewManager.show(ViewType.EMPLOYEE_ADD_OF_BOOKS);
    }

    @FXML
    public void goToListOfUsers(Event event) throws IOException {
        viewManager.show(ViewType.EMPLOYEE_LIST_OF_USERS);
    }

    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    public void goToLogout(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }

}

