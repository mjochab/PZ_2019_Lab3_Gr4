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
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
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
	public void start(Stage primaryStage) {
		viewManager.init(primaryStage);
	}

	@Override
	public void stop() {
		springContext.stop();
	}
}
