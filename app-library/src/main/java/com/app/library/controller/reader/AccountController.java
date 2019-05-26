package com.app.library.controller.reader;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
    private Text firstNameTextBox1;

    @FXML
    private TextField nameTextBox;

    @FXML
    private Text surnameTextBox1;

    @FXML
    private TextField surnameTextBox;

    @FXML
    private Label peselTextBox1;

    @FXML
    private TextField peselTextBox;

    @FXML
    private Label countryTextBox1;

    @FXML
    private TextField mailTextBox;

    @FXML
    private Text cityTextBox1;

    @FXML
    private TextField cityTextBox;

    @FXML
    private TextField streetTextBox;

    @FXML
    private Text codeTextBox1;

    @FXML
    private TextField codeTextBox;

    @FXML
    private TextField wojewodztwoTextBox;

    @FXML
    private TextField countryTextBox;

    @FXML
    private JFXButton saveChange;



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
