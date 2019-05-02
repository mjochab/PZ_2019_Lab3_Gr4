package com.app.library.controller.shared;

import com.app.library.model.Book;
import com.app.library.service.PersistenceService;
import com.app.library.utils.PersistenceKeys;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class IndividualBookController implements Initializable {

    @Autowired
    private ViewManager viewManager;

    @Autowired
    private PersistenceService persistenceService;

    @FXML
    private Text text_author = new Text();

    @FXML
    private Text year_text = new Text();

    @FXML
    private Text title_text = new Text();

    @FXML
    private Button btn = new Button();


    @FXML
    public void goToBookSearch() {
        viewManager.show(ViewType.READER_SEARCH_BOOKS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Book book = (Book) persistenceService.getStoredObject(PersistenceKeys.SINGLE_BOOK);
        setData(book.getAuthor(), book.getYearOfPublication(), book.getName());
    }

    private void setData(String author, int rok, String title) {
        text_author.setText("Autor: " + author);
        year_text.setText("Rok wydania: " + rok);
        title_text.setText(title);
    }
}
