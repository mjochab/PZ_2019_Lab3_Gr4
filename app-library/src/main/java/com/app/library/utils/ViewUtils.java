package com.app.library.utils;

import javafx.scene.control.Alert;
import org.springframework.stereotype.Component;

@Component
public class ViewUtils {

    public void showErrorAlert(AlertMessage message) {
        Alert alert = createAlert(message, Alert.AlertType.ERROR);
        alert.showAndWait();
    }

    public void showSuccessAlert(AlertMessage message) {
        Alert alert = createAlert(message, Alert.AlertType.CONFIRMATION);
        alert.showAndWait();
    }

    private Alert createAlert(AlertMessage message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(message.getTitle());
        alert.setHeaderText(message.getHeader());
        alert.setContentText(message.getContent());
        alert.setResizable(true);

        return alert;
    }
}
