package com.app.library.service;

import com.app.library.model.Book;
import com.app.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Service
@Validated
public class BookService {

    private BookRepository bookRepository;

    public Book findById(@Positive Integer id) { return bookRepository.getOne(id); }

    public List<Book> findAll() { return bookRepository.findAll(); }

    public List<Book> findByQueryIgnoreCase(String query) { return bookRepository.findByQuery(query.toLowerCase()); }

    public Book saveBook(@Valid Book book) { return bookRepository.save(book); }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
