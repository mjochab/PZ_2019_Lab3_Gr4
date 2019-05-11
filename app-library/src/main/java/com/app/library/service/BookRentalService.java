package com.app.library.service;

import com.app.library.model.BookRental;
import com.app.library.repository.BookRentalRepository;
import com.app.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookRentalService {

    @Autowired
    private BookRentalRepository bookRentalRepository;

    public BookRental findBySignature(UUID signature) { return bookRentalRepository.findBybookUnit(signature); }

    @Autowired
    public void setBookRentalRepository(BookRentalRepository bookRentalRepository) {
        this.bookRentalRepository = bookRentalRepository;
    }

}
