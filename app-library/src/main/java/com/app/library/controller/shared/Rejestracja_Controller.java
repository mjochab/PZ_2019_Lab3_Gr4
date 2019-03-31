package com.app.library.controller.shared;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class Rejestracja_Controller {

    @Autowired
    private ViewManager viewManager;

    @FXML
    public void go_to_home(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }

}
