package com.app.library.service;

import com.app.library.model.BookUnit;
import com.app.library.repository.BookUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookUnitService {

    private BookUnitRepository bookUnitRepository;

    public BookUnit findBySignature(UUID signature) {
        return bookUnitRepository.findBySignature(signature);
    }

    public List<BookUnit> findByBookId(int book_id) {
        return bookUnitRepository.findByBookId(book_id);
    }

    public void save(BookUnit bookUnit) {
        bookUnitRepository.save(bookUnit);
    }

    @Autowired
    public void setBookRepository(BookUnitRepository bookRepository) {
        this.bookUnitRepository = bookRepository;
    }

}
