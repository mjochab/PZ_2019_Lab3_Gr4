package com.app.library.controller;

import com.app.library.model.enumerate.RoleName;
import com.app.library.repository.RoleRepository;
import com.app.library.repository.UserRepository;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainController implements Initializable {

    @Autowired
    private ViewManager viewManager;

    // example autowired
    @Autowired
    private RoleRepository roleRepository;

    // example autowired
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(timeout = 3000)
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(roleRepository.findByName(RoleName.ROLE_EMPLOYEE));
        System.out.println(userRepository.findEmployees());
    }

    @FXML
    public void goToEmployer(Event event) throws IOException {
        viewManager.show(ViewType.EMPLOYEE_ACCOUNT);
    }

    @FXML
    public void goToReaderAccount() {
        viewManager.show(ViewType.READER_ACCOUNT);
    }

    @FXML
    public void goToRejestracja(Event event) throws IOException {
        viewManager.show(ViewType.READER_REGISTRATION);
    }

    @FXML
    public void goToZaloguj(Event event) throws IOException {
        viewManager.show(ViewType.SIGN_IN);
    }

    @FXML
    public void goToBoss(Event event) throws IOException {
        viewManager.show(ViewType.BOSS_ACCOUNT);
    }
}


