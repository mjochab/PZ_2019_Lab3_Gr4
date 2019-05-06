package com.app.library.service;

import com.app.library.model.Book;
import com.app.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private Integer lastClickedBookId = null;

    private BookRepository bookRepository;

    public Book findById(int id) {
        return bookRepository.findById(id);
    }

    public Book findByName(String name) {return bookRepository.findByName(name);}

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Integer getLastClickedBookId() {
        return lastClickedBookId;
    }

    public void setLastClickedBookId(Integer id) {
        this.lastClickedBookId = id;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
