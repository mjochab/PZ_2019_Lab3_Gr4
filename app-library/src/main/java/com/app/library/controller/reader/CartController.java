package com.app.library.controller.reader;

import com.app.library.model.Book;
import com.app.library.model.BookOrderUnit;
import com.app.library.model.BookUnit;
import com.app.library.model.BooksOrder;
import com.app.library.repository.BookOrderUnitRepository;
import com.app.library.repository.BookRepository;
import com.app.library.repository.BooksOrderRepository;
import com.app.library.service.*;
import com.app.library.utils.PersistenceKeys;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.*;


@Controller
public class CartController implements Initializable {

    @Autowired
    private ViewManager viewManager;

    @Autowired
    private PersistenceService persistenceService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookUnitService bookUnitService;

    @Autowired
    private BooksOrderService booksOrderService;

    @Autowired
    private BookUnitOrderService bookUnitOrderService;



    @FXML
    private TableView<List<String>> cartTable = new TableView();

    @FXML
    private TableColumn<List<String>, String> titleColumn, authorColumn, yearColumn, signatureColumn, statusColumn = new TableColumn<>();

    @FXML
    private Button orderButton = new Button();


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

    //ustawienie itemów dla tabeli
    private void setCartTable(List<BookUnit> lista){
        cartTable.setItems(getListForCartTable(lista));
    }


    //tworzenie listy, która będzie wrzucona do tabeli
    private ObservableList<List<String>> getListForCartTable(List<BookUnit> bookUnitList){
        ObservableList<List<String>> observableList = FXCollections.observableArrayList();
        for(int i = 0; i<bookUnitList.size(); i++){
            Book book = bookService.findById(bookUnitList.get(i).getBook().getId().intValue());
            List<String> stringList1 = new ArrayList<>();
            stringList1.add(book.getName());
            stringList1.add(book.getAuthor());
            stringList1.add(String.valueOf(book.getYearOfPublication()));
            stringList1.add(String.valueOf(bookUnitList.get(i).getSignature()));
            if(bookUnitList.get(i).isCheckedOut()){
                stringList1.add("Niedostępna");
            }else{
                stringList1.add("Dostępna");
            }
            observableList.addAll(stringList1);
        }
        return observableList;
    }

    @FXML
    private void submitOrder(){
        if(!persistenceService.getCart().isEmpty()) {
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();

//            dodawanie nowych rekordów do tabeli books_order
            BooksOrder booksOrder = new BooksOrder();
            booksOrder.setCreatedAt(date);
            booksOrder.setReadyToRelease(false);
            //tu będzie zalogowany user
            booksOrder.setReader(null);
            booksOrderService.save(booksOrder);

            for (int i = 0; i < persistenceService.getCart().size(); i++) {
                //zmiana checkedOut na true
                BookUnit bookUnit = bookUnitService.findBySignature(persistenceService.getCart().get(i).getSignature());
                bookUnit.setCheckedOut(true);
                bookUnitService.save(bookUnit);

                //dodawanie nowych rekordów do tabeli book_unit_order
                BookOrderUnit bookOrderUnit = new BookOrderUnit();
                bookOrderUnit.setBooksOrder(booksOrder);
                bookOrderUnit.setBookUnit(persistenceService.getCart().get(i));
                bookOrderUnit.setReadyToRent(false);
                bookUnitOrderService.save(bookOrderUnit);
            }



            persistenceService.cleanCart();
            viewManager.show(ViewType.READER_MY_ORDERS);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        List<BookUnit> bookUnitList = persistenceService.getCart();

        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        yearColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        signatureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));

        setCartTable(bookUnitList);





    }






}
