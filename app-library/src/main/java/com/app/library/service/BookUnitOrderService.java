package com.app.library.service;

import com.app.library.model.BookOrderUnit;
import com.app.library.repository.BookOrderUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookUnitOrderService {

    private BookOrderUnitRepository bookOrderUnitRepository;

    public BookOrderUnit save(BookOrderUnit entity) {
        return bookOrderUnitRepository.save(entity);
    }

    public List<BookOrderUnit> saveAll(List<BookOrderUnit> orderUnits) {
        return bookOrderUnitRepository.saveAll(orderUnits);
    }

    public BookOrderUnit findById(int id) {return bookOrderUnitRepository.findById(id);}

    public List<BookOrderUnit> findByBooksOrderId(Integer id) {
        return bookOrderUnitRepository.findByBooksOrderId(id);
    }

    @Autowired
    public void setBookOrderUnitRepository(BookOrderUnitRepository booksOrderRepository) {
        this.bookOrderUnitRepository = booksOrderRepository;
    }

}
