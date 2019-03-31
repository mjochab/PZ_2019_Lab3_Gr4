package com.app.library.controller.employer;

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
public class Account_employer_Controller {

    @Autowired
    private ViewManager viewManager;

    @FXML
    public void goToListBooks(Event event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/employer/books-list.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Ksiązki");
        stage.setScene(new Scene(root, 597, 853));
        stage.show();
    }


    @FXML
    public void goToListOrders(Event event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/employer/realized-orders.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Lista zamówień");
        stage.setScene(new Scene(root, 597, 853));
        stage.show();
    }

    @FXML
    public void goToAddBooks(Event event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/employer/adding-book.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Dodawanie książęk");
        stage.setScene(new Scene(root, 597, 853));
        stage.show();
    }

    @FXML
    public void goToListUsers(Event event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/employer/users-list.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Lista użytkowników");
        stage.setScene(new Scene(root, 597, 853));
        stage.show();
    }

    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }

    }

