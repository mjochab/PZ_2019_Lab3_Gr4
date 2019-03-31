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
public class AccountController {

   @Autowired
    private ViewManager viewManager;



    @FXML
    public void go_to_search_books(){
        viewManager.show(ViewType.READER_SEARCH_BOOKS);
    }

    @FXML
    public void go_to_my_orders(){
        viewManager.show(ViewType.READER_MY_ORDERS);
    }

    @FXML
    public void go_to_home(){
        viewManager.show(ViewType.MAIN);
    }


}
