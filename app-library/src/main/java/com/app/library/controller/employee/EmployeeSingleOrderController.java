package com.app.library.controller.employee;

import com.app.library.model.BookOrderUnit;
import com.app.library.model.BookRental;
import com.app.library.model.BooksOrder;
import com.app.library.service.BookRentalService;
import com.app.library.service.BookUnitOrderService;
import com.app.library.service.BooksOrderService;
import com.app.library.service.PersistenceService;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


@Controller
public class EmployeeSingleOrderController implements Initializable {

    @Autowired
    private PersistenceService persistenceService;

    @Autowired
    private BookUnitOrderService bookUnitOrderService;

    @Autowired
    private BookRentalService bookRentalService;

    @Autowired
    private ViewManager viewManager;

    @Autowired
    private BooksOrderService booksOrderService;


    @FXML
    private TableView<BookOrderUnit> orderTable = new TableView<>();

    @FXML
    private TableColumn<BookOrderUnit, String> signatureColumn, titleColumn = new TableColumn<>();

    @FXML
    private Button applyButton = new Button();

    @FXML
    public void goBack(){
        viewManager.show(ViewType.EMPLOYEE_READER_ORDERS);
    }

    @FXML
    public void realizeOrder(){
        List<BookOrderUnit> bookOrderUnits = bookUnitOrderService.findByBooksOrderId(persistenceService.getSelectedBooksOrder().getId());

        for(int i = 0; i<bookOrderUnits.size(); i++){
            BookRental bookRental = new BookRental();
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            bookRental.setDateOfRental(date);
            cal.add(Calendar.WEEK_OF_MONTH, 4);
            Date dateOfReturn = cal.getTime();
            bookRental.setDateOfReturn(dateOfReturn);
            bookRental.setBorrower(persistenceService.getEmployee());
            bookRental.setLender(persistenceService.getUser());
            bookRental.setBookOrderUnit(bookOrderUnits.get(i));
            bookRental.setBookUnit(bookOrderUnits.get(i).getBookUnit());
            bookRentalService.save(bookRental);
            BooksOrder booksOrder = booksOrderService.findById(bookOrderUnits.get(i).getBooksOrder().getId());
            booksOrder.setReadyToRelease(true);
            booksOrderService.save(booksOrder);
            BookOrderUnit bookOrderUnit = bookUnitOrderService.findById(bookOrderUnits.get(i).getId());
            bookOrderUnit.setReadyToRent(true);
            bookUnitOrderService.save(bookOrderUnit);
        }
        setDisableButtonIfRealized(bookOrderUnits);
    }

    private void setDisableButtonIfRealized(List<BookOrderUnit> bookOrderUnits){
            if(!bookRentalService.findByBookOrderUnitId(bookOrderUnits.get(0).getId()).isEmpty()){
                applyButton.setDisable(true);
            }
    }


    private void fillTable(List<BookOrderUnit> bookOrderUnits){
        orderTable.getItems().setAll(bookOrderUnits);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        signatureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBookUnit().getSignature().toString()));
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBookUnit().getBook().getName()));


        List<BookOrderUnit> bookOrderUnits = bookUnitOrderService.findByBooksOrderId(persistenceService.getSelectedBooksOrder().getId());
        fillTable(bookOrderUnits);

        setDisableButtonIfRealized(bookOrderUnits);

    }
}
