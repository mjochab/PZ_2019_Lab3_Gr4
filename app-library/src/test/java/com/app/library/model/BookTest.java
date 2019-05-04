package com.app.library.model;

import org.hamcrest.core.StringContains;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BookTest {

    private static final String TEST_AUTHOR = "TEST_AUTHOR";
    private static final String TEST_TITLE = "TEST_TITLE";
    private static final String TEST_COMPANY = "TEST_COMPANY";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldBuildBookWithBookUnits() {
        Book testBook = new Book.Builder()
                .quantity(3)
                .author(TEST_AUTHOR)
                .name(TEST_TITLE)
                .publishingCompany(TEST_COMPANY)
                .yearOfPublication(1990)
                .id(5)
                .build();

        int expectedBookUnits = 3;
        int expectedYearOfPublication = 1990;
        Integer expectedId = 5;

        assertEquals(testBook.getBookUnits().size(), expectedBookUnits);
        assertEquals(testBook.getAuthor(), TEST_AUTHOR);
        assertEquals(testBook.getName(), TEST_TITLE);
        assertEquals(testBook.getPublishingCompany(), TEST_COMPANY);
        assertEquals(testBook.getYearOfPublication(), expectedYearOfPublication);
        assertEquals(testBook.getId(), expectedId);
    }

    @Test
    public void shouldNotBuildBookWithNotPositiveQuantity() {
        exception.expect(Exception.class);
        exception.expectMessage(StringContains.containsString("Quantity can not be lower than 0."));

        new Book.Builder()
                .quantity(-1)
                .build();
    }

}