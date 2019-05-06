package com.app.library.utils;

import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.swing.*;
import javax.validation.constraints.Null;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class ViewUtilsTest {

    private static ViewUtils viewUtils;

    @BeforeClass
    public static void setUp() {
        viewUtils = new ViewUtils();
    }

    @Test(expected = NullPointerException.class)
    @TestInJfxThread
    public void shouldNotShowErrorAlert_NullPointerException() {
        viewUtils.showErrorAlert(null);
    }

    @Test(expected = NullPointerException.class)
    @TestInJfxThread
    public void shouldNotShowSuccessAlert_NullPointerException() {
        viewUtils.showSuccessAlert(null);
    }

    @Test
    @TestInJfxThread
    public void shouldShowErrorAlert() {
        Platform.runLater(() -> {
            AlertMessage alertMessage = getCorrectedAlertMessage();
            viewUtils.showErrorAlert(alertMessage);
        });
    }

    @Test
    @TestInJfxThread
    public void shouldShowSuccessAlert() {
        Platform.runLater(() -> {
            AlertMessage alertMessage = getCorrectedAlertMessage();
            viewUtils.showSuccessAlert(alertMessage);
        });
    }

    private AlertMessage getCorrectedAlertMessage() {
        return new AlertMessage.Builder()
                .content("content")
                .header("header")
                .title("title")
                .build();
    }
}