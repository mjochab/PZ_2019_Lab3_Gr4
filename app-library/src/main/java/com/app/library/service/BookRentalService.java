package com.app.library.service;

import com.app.library.model.BookRental;
import com.app.library.model.BooksOrder;
import com.app.library.repository.BookRentalRepository;
import com.app.library.repository.BookRepository;
import com.app.library.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookRentalService {

    @Autowired
    private BookRentalRepository bookRentalRepository;

    public BookRental findBySignature(UUID signature) { return bookRentalRepository.findBybookUnit(signature); }

    public List<BookRental> findByBookOrderUnitId(int id) {return bookRentalRepository.findByBookOrderUnitId(id);}

    public void save(BookRental entity) { bookRentalRepository.save(entity);}

    public List<BookRental> findAll() {return bookRentalRepository.findAll();}

    public List<BookRental> findByQueryIgnoreCaseAndCreatedAtBetween(String query, LocalDate orderDateFrom, LocalDate orderDateTo) {
        String searchQuery = query != null ? query : "";
        Date dateFrom = DateUtils.asDate(orderDateFrom);
        Date dateTo = DateUtils.asDate(orderDateTo);

        return bookRentalRepository.findByQueryAndCreatedAtBetween(searchQuery.toLowerCase(), dateFrom, dateTo);
    }

    @Autowired
    public void setBookRentalRepository(BookRentalRepository bookRentalRepository) {
        this.bookRentalRepository = bookRentalRepository;
    }

}
