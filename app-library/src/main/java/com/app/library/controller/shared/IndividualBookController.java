package com.app.library.controller.shared;

import com.app.library.controller.reader.CartController;
import com.app.library.model.Book;
import com.app.library.model.BookUnit;
import com.app.library.repository.BookUnitRepository;
import com.app.library.service.BookService;
import com.app.library.service.BookUnitService;
import com.app.library.service.PersistenceService;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


@Controller
public class IndividualBookController implements Initializable {

    @Autowired
    private ViewManager viewManager;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookUnitService bookUnitService;

    @Autowired
    private PersistenceService persistenceService;


    @FXML
    private TableView<List<String>> bookUnitTable = new TableView<>();

    @FXML
    private TableColumn<List<String>, String> signatureColumn, availibilityColumn = new TableColumn<>();

    @FXML
    private Text authorText,yearText, titleText, companyText = new Text();

    @FXML
    private Button goBackButton, addToCartButton = new Button();



    @FXML
    public void goToBookSearch() {
        viewManager.show(ViewType.READER_SEARCH_BOOKS);
    }


    private void disableButtonIfUnavailable(String status){
        if(status == "Niedostępna"){
            addToCartButton.setDisable(true);
        }
    }


    private void showBookData( Book book){
        authorText.setText(book.getAuthor());
        yearText.setText(String.valueOf(book.getYearOfPublication()));
        titleText.setText(book.getName());
        companyText.setText(book.getPublishingCompany());
    }


    private void setTableData(List<BookUnit> lista){
        ObservableList observableList = FXCollections.observableArrayList();

        for(int i = 0; i<lista.size(); i++){
            List<String> stringList = new ArrayList<>();
            stringList.add(lista.get(i).getSignature());
            if(lista.get(i).isCheckedOut()){
                stringList.add("Niedostępna");
            }else{
                stringList.add("Dostępna");
            }
            observableList.add(stringList);
        }
        bookUnitTable.setItems(observableList);
    }

    @FXML
    private void addToCart(){
            List<String> stringList = bookUnitTable.getSelectionModel().getSelectedItem();
            BookUnit bookUnit = bookUnitService.findBySignature(stringList.get(0));
            persistenceService.addToCart(bookUnit);
            viewManager.show(ViewType.READER_CART);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //pobranie książki przechowywanej w pamięci
        Book book = (Book) persistenceService.getStoredObject(PersistenceKeys.SINGLE_BOOK);


        signatureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        availibilityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));

        //wyświetlenie danych o pobranej książce
        showBookData(book);

        //wyświetlenie danych w bookUnitTable
        List<BookUnit> bookUnitList = bookUnitService.findByBookId(book.getId());
        setTableData(bookUnitList);



    }
}
