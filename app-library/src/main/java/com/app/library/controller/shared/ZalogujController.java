package com.app.library.controller.shared;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.io.IOException;
@Controller
public class ZalogujController {

    @Autowired
    private ViewManager viewManager;


    public void goToHome(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    private Button home;

    @FXML
    private TextField mailTextBox;

    @FXML
    private TextField passwordTextBox;


    @FXML
    void login(MouseEvent event) {

        String mail = mailTextBox.getText();
        String password = passwordTextBox.getText();

    }

}
