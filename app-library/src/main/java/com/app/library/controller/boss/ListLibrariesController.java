package com.app.library.controller.boss;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class ListLibrariesController {


    @Autowired
    private ViewManager viewManager;


    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    public void goToListEmployee(ActionEvent event) throws IOException {
        viewManager.show(ViewType.BOSS_LIST_OF_EMPLOYEE);
    }

    @FXML
    public void goToListLibraries(ActionEvent event) throws IOException {
        viewManager.show(ViewType.BOSS_LIST_OF_LIBRERIES);
    }

    @FXML
    public void goToAddEmployee(ActionEvent event) throws IOException {
        viewManager.show(ViewType.BOSS_ADD_EMPLOYEE);
    }

    @FXML
    public void goToAccount(ActionEvent event) throws IOException {
        viewManager.show(ViewType.BOSS_ACCOUNT);
    }

    @FXML
    public void goToLogout(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }


}

