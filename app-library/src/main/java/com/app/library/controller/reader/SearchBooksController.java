package com.app.library.controller.reader;

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
public class SearchBooksController implements Initializable {

    @FXML
    private JFXHamburger hamburgerButton;

    @Autowired
    private ViewManager viewManager;

    private HamburgerSlideCloseTransition burgerTask;

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        assert hamburgerButton != null : "fx:id=\"hamburgerButton\" was not injected: check your FXML file 'main.fxml'.";

        burgerTask = new HamburgerSlideCloseTransition(hamburgerButton);
        burgerTask.setRate(-1);
    }

    @FXML
    void hamburgerButtonClicked(MouseEvent event) {
        this.viewManager.show(ViewType.MAIN);
    }

    @FXML
    void hamburgerButtonPressed(MouseEvent event) {
        burgerTask.setRate(burgerTask.getRate() * -1);
        burgerTask.play();
    }

    @FXML
    public void goToAccount(Event event) {
        this.viewManager.show(ViewType.READER_ACCOUNT);
    }

}
