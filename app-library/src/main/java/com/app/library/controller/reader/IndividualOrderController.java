package com.app.library.controller.reader;

import com.app.library.model.Book;
import com.app.library.model.BookOrderUnit;
import com.app.library.model.BookRental;
import com.app.library.service.*;
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

import javax.validation.constraints.NotNull;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Klasa kontroler, zarządzająca działaniem widoku dla pojedynczego
 * zamówienia czytelnika.
 */
@Controller
public class IndividualOrderController implements Initializable {

    /**
     * Obiekt klasy PersistaneService, dzięki której przechowujemy dane globalnie,
     * np. dane zalogowanego użytkownika, koszyk itp..
     */
    @Autowired
    private PersistenceService persistenceService;

    /**
     * Obiekt klasy ViewManager, zarządającej scenami i przejściami między nimi.
     */
    @Autowired
    private ViewManager viewManager;

    /**
     * Obiekt klasy BookUnitOrderService, zarządzającej wypożyczeniami pojedynczych sygnatur książek.
     */
    @Autowired
    private BookUnitOrderService bookUnitOrderService;

    /**
     * Obiekt klasy BookUnitService, zarządzającej sygnaturami książek.
     */
    @Autowired
    private BookUnitService bookUnitService;

    /**
     * Obiekt klasy BookService, zarządzającej książkami, czyli zbiorem sygnatur.
     */
    @Autowired
    private BookService bookService;

    /**
     * Obiekt klasy BookRentalService, zarządzającej zatwierdzonymi wypożyczeniami.
     */
    @Autowired
    private BookRentalService bookRentalService;


    /**
     * Zainicjalizowana tablica, przechowująca sygnatury książek z jednego zamówienia.
     */
    @FXML
    private TableView<List<String>> bookUnitTable = new TableView();

    /**
     * Zainicjalizowane kolumny tablicy bookUnitTable.
     */
    @FXML
    private TableColumn<List<String>, String> signatureColumn, titleColumn, dateOfRentalColumn, expireDateColumn, statusColumn = new TableColumn<>();


    /**
     * Metoda, dzięki której przechodzimy do widoku zamówień czytelnika.
     */
    @FXML
    private void goToMyOrders(){
        viewManager.show(ViewType.READER_MY_ORDERS);
    }

    /**
     * Metoda ustawiająca listę, która wypełni tabelę
     * @param tableItems
     * Lista, która wypełni tabelę
     */
    private void setTableItems(ObservableList tableItems){
        bookUnitTable.setItems(tableItems);
    }


    /**
     * Metoda filtrująca listę sygnatur, oraz zwracająca listę danych do wyświetlenia w tabeli.
     * @param bookOrderUnits Lista sygnatur książek zawierających się w określonym zamówieniu
     * @return Przefiltrowana lista, która wypełni tabelę.
     */
    private ObservableList getListForTable(@NotNull List<BookOrderUnit> bookOrderUnits){
        ObservableList<List<String>> observableList = FXCollections.observableArrayList();
        Book book = new Book();
        for(int i = 0; i<bookOrderUnits.size(); i++){
            List<String> stringList = new ArrayList<>();
            stringList.add(bookOrderUnits.get(i).getBookUnit().getSignature().toString());

            book.setName(bookOrderUnits.get(i).getBookUnit().getBook().getName());
            stringList.add(book.getName());



            if(!bookRentalService.findByBookOrderUnitId(bookOrderUnits.get(i).getId()).isEmpty()){
                stringList.add(bookRentalService.findByBookOrderUnitId(bookOrderUnits.get(i).getId()).get(i).getDateOfRental().toString());
                stringList.add(bookRentalService.findByBookOrderUnitId(bookOrderUnits.get(i).getId()).get(i).getDateOfReturn().toString());
            }else {
                stringList.add(" ");
                stringList.add(" ");
            }

            //sprawdzenie czy książka została skompletowana
            if(!bookOrderUnits.get(i).isReadyToRent()){
                stringList.add("W trakcie kompletowania");
            } else{
                stringList.add("Gotowa");
            }

            observableList.addAll(stringList);
        }


        return observableList;
    }

    /**
     * Metoda wykonująca się automatycznie po przejściu do widoku.
     * Ustawiane są dane dla poszczególnych kolumn, oraz wywołanie metod wypełniających tabelę danymi.
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        signatureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        dateOfRentalColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        expireDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));

        List<BookOrderUnit> bookOrderUnits = bookUnitOrderService.findByBooksOrderId(persistenceService.getSelectedBooksOrder().getId());

        setTableItems(getListForTable(bookOrderUnits));

    }
}
