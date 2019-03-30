package com.app.library;

import com.app.library.view.ViewManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LibraryApplication extends Application {

	private ConfigurableApplicationContext springContext;

	@Autowired
	private ViewManager viewManager;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		springContext = SpringApplication.run(LibraryApplication.class);
		springContext.getAutowireCapableBeanFactory().autowireBean(this);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/shared/glowna.fxml"));
		primaryStage.setTitle("Strona Główna");
		primaryStage.setScene(new Scene(root, 597, 852));
		primaryStage.show();
	}

	@Override
	public void stop() {
		springContext.stop();
	}
}
