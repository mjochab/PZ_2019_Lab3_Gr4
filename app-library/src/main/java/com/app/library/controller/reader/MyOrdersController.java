package com.app.library.controller.reader;


import com.app.library.view.ViewManager;

import com.app.library.view.ViewType;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MyOrdersController {

    @Autowired
    private ViewManager viewManager;


    @FXML
    public void goToAccount(){
        viewManager.show(ViewType.READER_ACCOUNT);
    }

    @FXML
    public void goToHome(){
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    public void goToSearchBooks(){
        viewManager.show(ViewType.READER_SEARCH_BOOKS);
    }






}

