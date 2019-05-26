package com.app.library.controller.shared;


import com.app.library.model.enumerate.RoleName;
import com.app.library.service.UserService;
import com.app.library.utils.AlertMessage;
import com.app.library.utils.ViewUtils;
import com.app.library.utils.security.AuthorizationDto;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
@Controller
public class UserLoginController {

    private ViewManager viewManager;
    private UserService userService;
    private ViewUtils viewUtils;

    public void goToHome(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    private TextField emailTextBox;

    @FXML
    private TextField passwordTextBox;

    @FXML
    void login(MouseEvent event) {
        String email = emailTextBox.getText();
        String password = passwordTextBox.getText();

        try {
            AuthorizationDto authorizationDto = userService.loginUser(email, password);
            goToLoggedUserPanel(authorizationDto);
        } catch (Exception ex) {
            showLoginErrorMessage(ex.getMessage());
        }
    }

    private void goToLoggedUserPanel(AuthorizationDto authorizationDto) {
        if (authorizationDto.getRoleName().equals(RoleName.ROLE_EMPLOYEE)) {
            viewManager.show(ViewType.EMPLOYEE_ACCOUNT);
        }
        else if (authorizationDto.getRoleName().equals(RoleName.ROLE_READER)) {
            viewManager.show(ViewType.READER_ACCOUNT);
        }
        else if (authorizationDto.getRoleName().equals(RoleName.ROLE_DIRECTOR)) {
            viewManager.show(ViewType.BOSS_ACCOUNT);
        }
    }

    void showLoginErrorMessage(String messageContent) {
        AlertMessage message = new AlertMessage.Builder()
                .content(messageContent)
                .header("Błąd logowania")
                .build();

        viewUtils.showErrorAlert(message);
    }

    @FXML
    void goToReaderRegistration(ActionEvent event) {
        this.viewManager.show(ViewType.READER_REGISTRATION);
    }

    @Autowired
    public void setViewUtils(ViewUtils viewUtils) {
        this.viewUtils = viewUtils;
    }

    @Autowired
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
