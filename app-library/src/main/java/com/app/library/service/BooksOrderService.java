package com.app.library.service;

import com.app.library.model.BooksOrder;
import com.app.library.repository.BookOrderUnitRepository;
import com.app.library.repository.BooksOrderRepository;
import com.app.library.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BooksOrderService {

    private BooksOrderRepository booksOrderRepository;
    private BookOrderUnitRepository bookOrderUnitRepository;

    public List<BooksOrder> findAllOrders() { return booksOrderRepository.findAll(); }

    public List<BooksOrder> findByQueryIgnoreCaseAndCreatedAtBetween(String query, LocalDate orderDateFrom, LocalDate orderDateTo) {
        String searchQuery = query != null ? query : "";
        Date dateFrom = DateUtils.asDate(orderDateFrom);
        Date dateTo = DateUtils.asDate(orderDateTo);

        return booksOrderRepository.findByQueryAndCreatedAtBetween(searchQuery.toLowerCase(), dateFrom, dateTo);
    }

    @Autowired
    public void setBooksOrderRepository(BooksOrderRepository booksOrderRepository) {
        this.booksOrderRepository = booksOrderRepository;
    }

    @Autowired
    public void setBookOrderUnitRepository(BookOrderUnitRepository bookOrderUnitRepository) {
        this.bookOrderUnitRepository = bookOrderUnitRepository;
    }
}