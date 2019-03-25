package com.app.library.controller;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @FXML
    private JFXHamburger hamburgerButton;

    @FXML
    private void initialize() {
        HamburgerSlideCloseTransition burgerTask = new HamburgerSlideCloseTransition(hamburgerButton);
        burgerTask.setRate(-1);
        hamburgerButton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
            burgerTask.setRate(burgerTask.getRate()*-1);
            burgerTask.play();
        });
    }
}
