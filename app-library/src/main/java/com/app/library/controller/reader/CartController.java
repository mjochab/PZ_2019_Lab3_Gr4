package com.app.library.controller.reader;

import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


@Controller
public class CartController implements Initializable {

    @Autowired
    private ViewManager viewManager;

    @FXML
    private TableView<List<String>> table = new TableView();

    @FXML
    private TableColumn<List<String>, String> colTitle = new TableColumn<>();

    @FXML
    private TableColumn<List<String>, String> colAuthor = new TableColumn<>();

    @FXML
    private TableColumn<List<String>, String> colYearOfPublication = new TableColumn<>();

    @FXML
    private TableColumn<List<String>, String> colAvailable= new TableColumn<>();

    private ObservableList observableList = FXCollections.observableArrayList();


    @FXML
    public void goToHome(){
        viewManager.show(ViewType. MAIN);
    }

    @FXML
    public void goToSearchBooks(){
        viewManager.show(ViewType.READER_SEARCH_BOOKS);
    }

    @FXML
    public void goToOrders(){
        viewManager.show(ViewType.READER_MY_ORDERS);
    }

    @FXML
    public void goToAccount(){
        viewManager.show(ViewType.READER_ACCOUNT);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        colAuthor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        colYearOfPublication.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        colAvailable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        table.setItems(observableList);

    }


    public void addToObservableList(List<String> lista){
        this.observableList.add(lista);
    }



}
