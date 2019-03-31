package com.app.library.controller.employer;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
@Controller
public class AccountEmployerController {

    @Autowired
    private ViewManager viewManager;

    @FXML
    public void goToListBooks(Event event) throws IOException {
        viewManager.show(ViewType.EMPLOYER_LIST_OF_BOOKS);
    }


    @FXML
    public void goToListOrders(Event event) throws IOException {
        viewManager.show(ViewType.EMPLOYER_REALIZED_ORDERS);
    }

    @FXML
    public void goToAddBooks(Event event) throws IOException {
        viewManager.show(ViewType.EMPLOYER_ADD_OF_BOOKS);
    }

    @FXML
    public void goToListUsers(Event event) throws IOException {
        viewManager.show(ViewType.EMPLOYER_LIST_OF_USERS);
    }

    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }


    }

