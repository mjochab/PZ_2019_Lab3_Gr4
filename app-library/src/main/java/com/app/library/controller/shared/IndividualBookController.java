package com.app.library.controller.shared;

import com.app.library.view.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class IndividualBookController {

    @Autowired
    private ViewManager viewManager;

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
               Stage stage = (Stage)btn.getScene().getWindow();
               stage.close();
    }



    @FXML
    public void setData(String author, int rok, String title){
        text_author.setText("Autor: "+author);
        year_text.setText("Rok wydania: "+rok);
        title_text.setText(title);
    }
}
