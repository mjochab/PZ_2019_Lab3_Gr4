package com.app.library.controller.employer;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Account_employer_Controller {



    @FXML
    public void go_to_list_books(Event event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/employer/books-list.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Ksiązki");
        stage.setScene(new Scene(root, 597, 853));
        stage.show();
    }


    @FXML
    public void go_to_list_orders(Event event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/employer/realized-orders.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Lista zamówień");
        stage.setScene(new Scene(root, 597, 853));
        stage.show();
    }

    @FXML
    public void go_to_add_books(Event event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/employer/adding-book.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Dodawanie książęk");
        stage.setScene(new Scene(root, 597, 853));
        stage.show();
    }

    @FXML
    public void go_to_list_users(Event event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/employer/users-list.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Lista użytkowników");
        stage.setScene(new Scene(root, 597, 853));
        stage.show();
    }

    @FXML
    public void go_to_home(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/shared/glowna.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Biblioteka");
        stage.setScene(new Scene(root, 597, 852));
        stage.show();
    }

}