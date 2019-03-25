package com.app.library.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ViewManager {

    private static final String VIEWS_DIR = "/fxml/";
    private static final String VIEWS_EXTENSION = ".fxml";

    private ConfigurableApplicationContext context;
    private Stage stage;
    private FXMLLoader fxmlLoader = new FXMLLoader();

    public void init(Stage stage) {
        this.stage = stage;

        Parent mainWindow = getView(ViewType.MAIN.getFxmlName());
        Scene scene = new Scene(mainWindow, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void show(ViewType viewType) {
        Parent parent = getView(viewType.getFxmlName());
        stage.getScene().setRoot(parent);
    }

    private Parent getView(String viewName) {
        String viewPath = getViewPath(viewName);
        fxmlLoader.setControllerFactory(context::getBean);
        fxmlLoader.setLocation(getClass().getResource(viewPath));

        try {
            return (Parent) fxmlLoader.load();
        } catch (IOException e) {
            return null;
        }
    }

    private String getViewPath(String viewName) {
        StringBuilder builder = new StringBuilder();
        builder.append(VIEWS_DIR);
        builder.append(viewName);
        builder.append((VIEWS_EXTENSION));

        return builder.toString();
    }

    @Autowired
    public void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }
}