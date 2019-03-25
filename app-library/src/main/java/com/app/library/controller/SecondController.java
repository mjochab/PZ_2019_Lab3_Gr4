package com.app.library.controller;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class SecondController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXHamburger hamburgerButton;

    private ViewManager viewManager;

    @FXML
    private void initialize() {
        assert hamburgerButton != null : "fx:id=\"hamburgerButton\" was not injected: check your FXML file 'main.fxml'.";

        HamburgerSlideCloseTransition burgerTask = new HamburgerSlideCloseTransition(hamburgerButton);
        burgerTask.setRate(-1);
        hamburgerButton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            burgerTask.setRate(burgerTask.getRate() * -1);
            burgerTask.play();
        });

        hamburgerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            viewManager.show(ViewType.MAIN);
        });
    }

    @Autowired
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
