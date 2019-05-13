package com.app.library.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class AlertMessageTest {

    private static final String TEST_CONTENT = "content";
    private static final String TEST_TITLE = "title";
    private static final String TEST_HEADER = "header";

    @Test
    public void shouldBuildCorrectedAlertMessage() {
        AlertMessage testMessage = new AlertMessage.Builder()
                .title(TEST_TITLE)
                .header(TEST_HEADER)
                .content(TEST_CONTENT)
                .build();

        assertEquals(testMessage.getContent(), TEST_CONTENT);
        assertEquals(testMessage.getTitle(), TEST_TITLE);
        assertEquals(testMessage.getHeader(), TEST_HEADER);
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotBuildAlertMessage_NullPointerOnTitle() {
        new AlertMessage.Builder()
                .header(TEST_HEADER)
                .content(TEST_CONTENT)
                .title(null)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotBuildAlertMessage_NullPointerOnHeader() {
        new AlertMessage.Builder()
                .title(TEST_TITLE)
                .content(TEST_CONTENT)
                .header(null)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotBuildAlertMessage_NullPointerOnContent() {
        new AlertMessage.Builder()
                .title(TEST_TITLE)
                .header(TEST_HEADER)
                .build();
    }
}