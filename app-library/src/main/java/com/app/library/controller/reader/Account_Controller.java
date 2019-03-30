package com.app.library.controller.reader;

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

public class Account_Controller {

    @FXML
    private Button my_orders;


    @FXML
    public void go_to_my_orders(Event event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/reader/my_orders.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Konto");
        stage.setScene(new Scene(root, 597, 852));
        stage.show();
    }

    @FXML
    public void go_to_home(Event event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/shared/glowna.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Strona Główna");
        stage.setScene(new Scene(root, 597, 852));
        stage.show();
    }

    @FXML
    public void go_to_search_books(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/reader/search-books.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Wyszukaj książkę");
        stage.setScene(new Scene(root, 1280, 800));
        stage.show();
    }


}
