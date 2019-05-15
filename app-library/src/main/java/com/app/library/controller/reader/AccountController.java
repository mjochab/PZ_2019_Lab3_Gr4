package com.app.library.controller.reader;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {

    @Autowired
    private ViewManager viewManager;

    @FXML
    private JFXButton cart;

    @FXML
    private JFXButton home;

    @FXML
    private JFXButton search;

    @FXML
    private JFXButton my_orders;

    @FXML
    private JFXButton account;

    @FXML
    private JFXButton logout;

    @FXML
    private Label mailTextBox;

    @FXML
    private Label cardTextBox;

    @FXML
    private Label streetTextBox1;

    @FXML
    private Label zipCodeTextBox2;

    @FXML
    private Label stateTextBox3;

    @FXML
    private Label cityTextBox31;

    @FXML
    private Label countryTextBox311;

    @FXML
    private Label streetTextBox;

    @FXML
    private Label peselTextBox;

    @FXML
    private Label firstNameTextBox;

    @FXML
    private Label surnameTextBox;

    @FXML
    void editMail(MouseEvent event) {

    }

    @FXML
    public void goToSearchBooks() {
        viewManager.show(ViewType.READER_SEARCH_BOOKS);
    }

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


}
