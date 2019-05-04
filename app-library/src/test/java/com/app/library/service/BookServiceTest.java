package com.app.library.service;

import com.app.library.model.Book;
import com.app.library.model.Library;
import com.app.library.repository.BookRepository;
import com.app.library.repository.MockedBookRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    private static final String TEST_AUTHOR = "TEST_AUTHOR";

    private static BookService bookService;

    @BeforeClass
    public static void setUp() throws Exception {
        bookService = Mockito.spy(BookService.class);
        bookService.setBookRepository(Mockito.mock(MockedBookRepository.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotSaveBookAsNull() {
        Mockito.when(bookService.saveBook(Mockito.any(Book.class))).thenCallRealMethod();
        bookService.saveBook(null);
    }

    @Test
    public void shouldSaveValidBook() {
        Book book = new Book.Builder()
                .quantity(1)
                .author(TEST_AUTHOR)
                .name("TEST_TITLE")
                .library(new Library())
                .publishingCompany("TEST_COMPANY")
                .yearOfPublication(2000)
                .build();

        Mockito.when(bookService.saveBook(book)).thenCallRealMethod();
        Book savedBook = bookService.saveBook(book);

        assertEquals(savedBook.getAuthor(), TEST_AUTHOR);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldNotSaveBookWithoutBookUnits() {
        Book book = new Book.Builder()
                .author(TEST_AUTHOR)
                .name("TEST_TITLE")
                .library(new Library())
                .publishingCompany("TEST_COMPANY")
                .yearOfPublication(2000)
                .build();

        Mockito.when(bookService.saveBook(book)).thenCallRealMethod();
        bookService.saveBook(book);
    }
}