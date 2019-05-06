package com.app.library.controller.reader;


import com.app.library.model.Book;
import com.app.library.repository.BookRepository;
import com.app.library.service.BookService;
import com.app.library.service.PersistenceService;
import com.app.library.utils.PersistenceKeys;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class SearchBooksController implements Initializable {



    @Autowired
    private ViewManager viewManager;

    @Autowired
    private PersistenceService persistenceService;

    @Autowired
    BookService bookService;


    @FXML
    private JFXTextField search_field = new JFXTextField();

    @FXML
    private TableView<List<String>> table = new TableView();

    @FXML
    private TableColumn<List<String>, String> colTitle, colAuthor, colPublishingCompany, colYearOfPublication = new TableColumn<>();


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


    //filtrowanie danych w tabeli
    private void filteringTable(){
        ObservableList data =  table.getItems();
        search_field.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                table.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<List<String>> subentries = FXCollections.observableArrayList();
            long count = table.getColumns().stream().count();
            for (int i = 0; i < table.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + table.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(table.getItems().get(i));
                        break;
                    }
                }
            }
            table.setItems(subentries);
        });
    }

    @FXML
    private void goToIndividualBooksView(){
        //przejście do widoku indywidualnego książki na onDoubleClick
        table.setRowFactory( tv -> {
            TableRow<List<String>> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2) {
                    List<String> table_row = row.getItem();
                    Book book = bookService.findByName(table_row.get(0));
                    persistenceService.addToStore(PersistenceKeys.SINGLE_BOOK, book);
                    viewManager.show(ViewType.SINGLE_BOOK);
                }
            });
            return row;
        });
    }

    private void fillTable(){
        //tworzenie listy i wstawienie jej do tabeli
        List<Book> list = bookService.findAll();
        ObservableList listaa = FXCollections.observableArrayList();
        for(int i =0; i<list.size(); i++) {
            List<String> listToTable = new ArrayList<>();
            listToTable.add(list.get(i).getName());
            listToTable.add(list.get(i).getAuthor());
            listToTable.add(list.get(i).getPublishingCompany());
            listToTable.add(String.valueOf(list.get(i).getYearOfPublication()));

            listaa.add(listToTable);
        }
        table.setItems(listaa);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //określenie typów wartości w kolumnach
        colTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        colAuthor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        colPublishingCompany.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        colYearOfPublication.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));


        //wypełnienie tabeli danymi z bazy
        fillTable();

        //metoda pozwalająca na przejście do indywidualnego widoku książki po dwukrotnym kliknięciu na rząd w tabeli
        goToIndividualBooksView();

        //metoda filtrująca tabelę
        filteringTable();


    }
}



