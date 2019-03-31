package com.app.library.controller.employee;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ListBooksController {

    @Autowired
    private ViewManager viewManager;

    @FXML
    public void goToEmployeeAccount() {
        viewManager.show(ViewType.EMPLOYEE_ACCOUNT);
    }

    @FXML
    public void goToListBooks() {
        viewManager.show(ViewType.EMPLOYEE_LIST_OF_BOOKS);
    }

    @FXML
    public void goToRealizedOrders() {
        viewManager.show(ViewType.EMPLOYEE_REALIZED_ORDERS);
    }

    @FXML
    public void goToListUsers() {
        viewManager.show(ViewType.EMPLOYEE_LIST_OF_USERS);
    }

    @FXML
    public void goToHome() {
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    public void goToAddBooks() {
        viewManager.show(ViewType.EMPLOYEE_ADD_OF_BOOKS);
    }

}
