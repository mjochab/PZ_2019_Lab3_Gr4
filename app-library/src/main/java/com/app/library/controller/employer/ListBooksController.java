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
public class ListBooksController {

    @Autowired
    private ViewManager viewManager;

    @FXML
    public void goToEmployerAccount(){
        viewManager.show(ViewType.EMPLOYER_ACCOUNT);
    }

    @FXML
    public void goToListBooks(){
        viewManager.show(ViewType.EMPLOYER_LIST_OF_BOOKS);
    }

    @FXML
    public void goToRealizedOrders(){
        viewManager.show(ViewType.EMPLOYER_REALIZED_ORDERS);
    }

    @FXML
    public void goToListUsers(){
        viewManager.show(ViewType.EMPLOYER_LIST_OF_USERS);
    }

    @FXML
    public void goToHome(){
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    public void goToAddBooks() { viewManager.show(ViewType.EMPLOYER_ADD_OF_BOOKS);}

}

