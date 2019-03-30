package com.app.library.controller.reader;



import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class My_orders_Controller implements Initializable {

    @FXML
    private JFXHamburger hamburgerButton;

    private ViewManager viewManager;

    private HamburgerSlideCloseTransition burgerTask;

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        assert hamburgerButton != null : "fx:id=\"hamburgerButton\" was not injected: check your FXML file 'main.fxml'.";

        burgerTask = new HamburgerSlideCloseTransition(hamburgerButton);
        burgerTask.setRate(-1);
    }

    @FXML
    void hamburgerButtonPressed(MouseEvent event) {
        burgerTask.setRate(burgerTask.getRate() * -1);
        burgerTask.play();
    }

    @FXML
    void hamburgerButtonClicked(MouseEvent event) {
        viewManager.show(ViewType.READER_SEARCH_BOOKS);
    }

    @Autowired
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @FXML
//    przejście do zakładki "konto"
        public void go_to_account(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/reader/account.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Konto");
        stage.setScene(new Scene(root, 597, 852));
        stage.show();
    }

    public void go_to_home(ActionEvent event) throws Exception{
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
        stage.setScene(new Scene(root, 597, 852));
        stage.show();
    }



}

