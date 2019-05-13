package com.app.library.controller.boss;

import com.app.library.model.Address;
import com.app.library.model.Book;
import com.app.library.model.BooksOrder;
import com.app.library.model.Library;
import com.app.library.service.LibraryService;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



@Controller
public class ListLibrariesController implements Initializable {

    @Autowired
    private ViewManager viewManager;
    private LibraryService libraryService;

    @FXML
    private TableView<Library> librariesTable;

    @FXML
    private TableColumn<Library, Number> idColumn;

    @FXML
    private TableColumn<Library, String> nameColumn;

    @FXML
    private TableColumn<Library, String> zipCodeColumn;

    @FXML
    private TableColumn<Library, String> streetColumn;

    @FXML
    private TableColumn<Library, String> townColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        zipCodeColumn.setCellValueFactory(this::getLibraryZipCode);
        townColumn.setCellValueFactory(this::getLibraryTown);
        streetColumn.setCellValueFactory(this::getLibraryStreet);

        List<Library> libraries = libraryService.findAll();

        setTableItems(libraries);
    }


    private SimpleStringProperty getLibraryStreet(TableColumn.CellDataFeatures<Library, String> cellData) {
        return new SimpleStringProperty(
                cellData.getValue().getAddress().getStreet()
        );
    }
    private SimpleStringProperty getLibraryZipCode(TableColumn.CellDataFeatures<Library, String> cellData) {
        return new SimpleStringProperty(
                cellData.getValue().getAddress().getZipCode()
        );
    }
    private SimpleStringProperty getLibraryTown(TableColumn.CellDataFeatures<Library, String> cellData) {
        return new SimpleStringProperty(
                cellData.getValue().getAddress().getCity()
        );
    }


    private void setTableItems(List<Library> libraries) {
        librariesTable.getItems().setAll(libraries);
    }

    @Autowired
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Autowired
    public void setLibraryService(LibraryService libraryService) { this.libraryService = libraryService; }

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
        viewManager.show(ViewType.BOSS_LIST_OF_LIBRERIES);
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

