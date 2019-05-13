package com.app.library.repository;

import com.app.library.model.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRentalRepository extends JpaRepository<BookRental, Integer> {

    BookRental findBybookUnit(UUID signature);

    List<BookRental> findByBookOrderUnitId(int id);

    @Override
    <S extends BookRental> S save(S entity);
}
