package com.app.library.controller;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainController {

    @FXML
    public void go_to_employer(Event event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/employer/account-employer.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Konto");
        stage.setScene(new Scene(root, 757, 877));
        stage.show();
    }

    @FXML
    public void go_to_profil(Event event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/reader/account.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Konto");
        stage.setScene(new Scene(root, 558, 853));
        stage.show();
    }


}


