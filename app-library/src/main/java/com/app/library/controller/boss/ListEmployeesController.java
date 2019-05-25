package com.app.library.controller.boss;

import com.app.library.model.EmployeeOfLibrary;
import com.app.library.service.EmployeeOfLibraryService;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ListEmployeesController implements Initializable {

    @Autowired
    private ViewManager viewManager;
    private EmployeeOfLibraryService employeeOfLibraryService;

    @FXML
    private TableView<EmployeeOfLibrary> employeesTable;

    @FXML
    private TableColumn<EmployeeOfLibrary, Number> idColumn;

    @FXML
    private TableColumn<EmployeeOfLibrary, String> firstNameColumn;

    @FXML
    private TableColumn<EmployeeOfLibrary, String> surnameColumn;

    @FXML
    private TableColumn<EmployeeOfLibrary, String> peselColumn;

    @FXML
    private TableColumn<EmployeeOfLibrary, String> libraryColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(this::getFirstName);
//        surnameColumn.setCellValueFactory(this::getSurname);
//        peselColumn.setCellValueFactory(this::getPesel);
//        libraryColumn.setCellValueFactory(this::getLibraryName);


        List<EmployeeOfLibrary> employees = employeeOfLibraryService.findAll();

        setTableItems(employees);
    }



    private SimpleStringProperty getFirstName(TableColumn.CellDataFeatures<EmployeeOfLibrary, String> cellData) {
        return new SimpleStringProperty(
                cellData.getValue().getUser().getFirstName()
        );
    }

    private SimpleStringProperty getSurname(TableColumn.CellDataFeatures<EmployeeOfLibrary, String> cellData) {
        return new SimpleStringProperty(
                cellData.getValue().getUser().getSurname()
        );
    }

    private SimpleStringProperty getPesel(TableColumn.CellDataFeatures<EmployeeOfLibrary, String> cellData) {
        return new SimpleStringProperty(
                cellData.getValue().getUser().getPesel()
        );
    }

    private SimpleStringProperty getLibraryName(TableColumn.CellDataFeatures<EmployeeOfLibrary, String> cellData) {
        return new SimpleStringProperty(
                cellData.getValue().getLibrary().getName()
        );
    }

    private void setTableItems(List<EmployeeOfLibrary> employees) {
        employeesTable.getItems().setAll(employees);
    }

    @Autowired
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Autowired
    public void setEmployeeOfLibraryService(EmployeeOfLibraryService employeeOfLibraryService) { this.employeeOfLibraryService = employeeOfLibraryService; }

    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }

    @FXML
    public void goToListEmployee(ActionEvent event) throws IOException {
        viewManager.show(ViewType.BOSS_LIST_OF_EMPLOYEE);
    }

    @FXML
    public void goToListLibraries(ActionEvent event) throws IOException {
        viewManager.show(ViewType.BOSS_LIST_OF_LIBRARIES);
    }

    @FXML
    public void goToAddEmployee(ActionEvent event) throws IOException {
        viewManager.show(ViewType.BOSS_ADD_EMPLOYEE);
    }

    @FXML
    public void goToAccount(ActionEvent event) throws IOException {
        viewManager.show(ViewType.BOSS_ACCOUNT);
    }

    @FXML
    public void goToLogout(ActionEvent event) throws IOException {
        viewManager.show(ViewType.MAIN);
    }

}