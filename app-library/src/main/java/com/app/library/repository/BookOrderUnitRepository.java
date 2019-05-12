package com.app.library.repository;

import com.app.library.model.BookOrderUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookOrderUnitRepository extends JpaRepository<BookOrderUnit, Integer> {

    @Override
    <S extends BookOrderUnit> S save(S entity);

    List<BookOrderUnit> findByBooksOrderId(Integer id);




}
