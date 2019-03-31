package com.app.library.controller.employer;

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
public class AddingBookController {

    @Autowired
    private ViewManager viewManager;

    @FXML
    public void go_to_employer_account(){
        viewManager.show(ViewType.EMPLOYER_ACCOUNT);
    }

    @FXML
    public void go_to_list_of_books(){
        viewManager.show(ViewType.EMPLOYER_LIST_OF_BOOKS);
    }

    @FXML
    public void go_to_realized_orders(){
        viewManager.show(ViewType.EMPLOYER_REALIZED_ORDERS);
    }

    @FXML
    public void go_to_list_of_users(){
        viewManager.show(ViewType.EMPLOYER_LIST_OF_USERS);
    }

    @FXML
    public void go_to_home(){
        viewManager.show(ViewType.MAIN);
    }

}
