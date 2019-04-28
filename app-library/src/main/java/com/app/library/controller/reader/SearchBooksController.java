package com.app.library.controller.reader;

import ch.qos.logback.core.util.Loader;
import com.app.library.controller.shared.IndividualBookController;
import com.app.library.model.Book;
import com.app.library.repository.BookRepository;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class SearchBooksController implements Initializable {



    @Autowired
    private ViewManager viewManager;

    @Autowired
    BookRepository bookRepository;

    @FXML
    private TableView table = new TableView();

    @FXML
    private TableColumn colTitle = new TableColumn();

    @FXML
    private TableColumn colAuthor = new TableColumn();

    @FXML
    private TableColumn colPublishingCompany = new TableColumn();

    @FXML
    private TableColumn colYearOfPublication = new TableColumn();

    @FXML
    private TableColumn colAvailable = new TableColumn();





    @FXML
    public void goToAccount() {
        viewManager.show(ViewType.READER_ACCOUNT);
    }

    @FXML
    public void goToHome(){
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    public void goToOrders(){
        viewManager.show(ViewType.READER_MY_ORDERS);
    }

    @FXML
    public void goToCart(){
        viewManager.show(ViewType.READER_CART);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {





        colYearOfPublication.setCellValueFactory(new PropertyValueFactory<>("yearOfPublication"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colPublishingCompany.setCellValueFactory(new PropertyValueFactory<>("publishingCompany"));


        List<Book> list = bookRepository.findAll();
        ObservableList listaa = FXCollections.observableArrayList(list);
        table.setItems(listaa);



        table.setRowFactory( tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(event.getClickCount() == 2) {
                        Book book = row.getItem();
                        //jest stworzony viewManager, ale musiałem zainicjalizować Controller, dlatego wszystko napisałem ręcznie
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/fxml/shared/individual-book's-view.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //tutaj nie wiem jak pobrać Controller z klasy ViewManager
                        IndividualBookController individualBookController = loader.getController();
                        individualBookController.setData(book.getAuthor(), book.getYearOfPublication(),book.getName());

                        Parent p = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(p));
                        stage.show();



                    }
                }
            });
            return row;
        });


    }
}



