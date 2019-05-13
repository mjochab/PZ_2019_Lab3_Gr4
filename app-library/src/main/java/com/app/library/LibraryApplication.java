package com.app.library;

import com.app.library.model.User;
import com.app.library.service.PersistenceService;
import com.app.library.service.UserService;
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

	@Autowired
	private UserService userService;

	@Autowired
	private PersistenceService persistenceService;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		springContext = SpringApplication.run(LibraryApplication.class);
		springContext.getAutowireCapableBeanFactory().autowireBean(this);
		setLoggedUser();
		setLoggedEmployee();
	}

	@Override
	public void start(Stage primaryStage) {
		viewManager.init(primaryStage);
	}

	@Override
	public void stop() {
		springContext.stop();
	}

	//metoda tymczasowa, ustawienie akualnie zalogowanego readera
	private void setLoggedUser(){
		//w bazie user ma id = 2
		User user = userService.findById(2);
		persistenceService.setUser(user);
	}

	private void setLoggedEmployee(){
		//w bazie user ma id = 2
		User user = userService.findById(3);
		persistenceService.setEmployee(user);
	}

}
