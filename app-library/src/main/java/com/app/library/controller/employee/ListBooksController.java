package com.app.library.controller.employee;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ListBooksController {

    @Autowired
    private ViewManager viewManager;

    public void goToHome(ActionEvent event) {
        viewManager.show(ViewType.MAIN);
    }
}
