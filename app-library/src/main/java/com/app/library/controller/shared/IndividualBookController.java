package com.app.library.controller.shared;

import com.app.library.controller.reader.CartController;
import com.app.library.view.ViewManager;
import com.app.library.view.ViewType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


@Controller
public class IndividualBookController {

    @Autowired
    private ViewManager viewManager;

    @FXML
    private Text author_text = new Text();

    @FXML
    private Text year_text = new Text();

    @FXML
    private Text title_text = new Text();

    @FXML
    private Text status_text = new Text();

    @FXML
    private Button btn = new Button();

    @FXML
    public void goToBookSearch() {
               viewManager.show(ViewType.READER_SEARCH_BOOKS);
    }

    @FXML
    public void addToCart(){

        List<String> lista = new ArrayList<>();
        lista.add(title_text.getText());
        lista.add(author_text.getText());
        lista.add(year_text.getText());
        lista.add(status_text.getText());

        viewManager.show(ViewType.READER_CART);
        CartController cartController = viewManager.getFxmlLoader().getController();
        cartController.addToObservableList(lista);


    }



    @FXML
    public void setData( String title, String author, int rok, String status){
        author_text.setText(author);
        year_text.setText(String.valueOf(rok));
        title_text.setText(title);
        status_text.setText(status);
    }
}
