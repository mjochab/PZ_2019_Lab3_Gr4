package com.app.library.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ViewUtils {

    public void showErrorAlert(AlertMessage message) {
        Alert alert = createAlert(Objects.requireNonNull(message), Alert.AlertType.ERROR);
        alert.showAndWait();
    }

    public void showSuccessAlert(AlertMessage message) {
        Alert alert = createAlert(Objects.requireNonNull(message), Alert.AlertType.CONFIRMATION);
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

    public <T> void handleSpinnerValueChanged(Spinner<T> spinner) {
        if (!spinner.isEditable()) return;
        String text = spinner.getEditor().getText();
        SpinnerValueFactory<T> valueFactory = spinner.getValueFactory();
        if (valueFactory != null) {
            StringConverter<T> converter = valueFactory.getConverter();
            if (converter != null) {
                T value = converter.fromString(text);
                valueFactory.setValue(value);
            }
        }
    }
}
