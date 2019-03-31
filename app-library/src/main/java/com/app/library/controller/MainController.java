package com.app.library.controller;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.event.Event;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private ViewManager viewManager;

    @FXML
    public void goToEmployer(Event event) throws IOException {
        viewManager.show(ViewType.EMPLOYER_ACCOUNT);
    }

    @FXML
    public void goToReaderAccount() {
        viewManager.show(ViewType.READER_ACCOUNT);
    }

    @FXML
    public void goToRejestracja(Event event) throws IOException {
        viewManager.show(ViewType.REGISTER);
    }

    @FXML
    public void goToZaloguj(Event event) throws IOException {
        viewManager.show(ViewType.SIGN_IN);
    }

}


