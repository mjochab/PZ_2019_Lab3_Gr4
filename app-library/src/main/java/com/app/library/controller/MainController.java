package com.app.library.controller;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    
    @FXML
    private JFXHamburger hamburgerButton;

    private ViewManager viewManager;

    private HamburgerSlideCloseTransition burgerTask;

    @FXML
    private void initialize() {
        assert hamburgerButton != null : "fx:id=\"hamburgerButton\" was not injected: check your FXML file 'main.fxml'.";

        burgerTask = new HamburgerSlideCloseTransition(hamburgerButton);
        burgerTask.setRate(-1);
    }

    @FXML
    void hamburgerButtonPressed(MouseEvent event) {
        burgerTask.setRate(burgerTask.getRate() * -1);
        burgerTask.play();
    }

    @FXML
    void hamburgerButtonClicked(MouseEvent event) {
        viewManager.show(ViewType.SECOND);
    }

    @Autowired
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void test(){
        Syste.out.println("Wypisz");
    }


    public static void main(String[] args) {
        launch(args);


    }

}
