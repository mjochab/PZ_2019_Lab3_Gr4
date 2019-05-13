package com.app.library.service;

import com.app.library.model.BookRental;
import com.app.library.repository.BookRentalRepository;
import com.app.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookRentalService {

    @Autowired
    private BookRentalRepository bookRentalRepository;

    public BookRental findBySignature(UUID signature) { return bookRentalRepository.findBybookUnit(signature); }

    public List<BookRental> findByBookOrderUnitId(int id) {return bookRentalRepository.findByBookOrderUnitId(id);}

    public void save(BookRental entity) { bookRentalRepository.save(entity);}

    @Autowired
    public void setBookRentalRepository(BookRentalRepository bookRentalRepository) {
        this.bookRentalRepository = bookRentalRepository;
    }

}
