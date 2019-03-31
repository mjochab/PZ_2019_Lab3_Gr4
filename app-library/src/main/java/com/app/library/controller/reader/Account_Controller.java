package com.app.library.controller.reader;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
@Controller
public class Account_Controller {

    @FXML
    private Button my_orders;


    @Autowired
    private ViewManager viewManager;

    public void goToMyOrders(Event event) throws IOException {
        viewManager.show(ViewType.MY_ORDERS);
    }

    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    public void goToSearchBooks(ActionEvent event) throws IOException {
        viewManager.show(ViewType.READER_SEARCH_BOOKS);
    }


}
