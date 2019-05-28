package com.app.library.repository;

import com.app.library.model.BookRental;
import com.app.library.model.BookUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookRentalRepository extends JpaRepository<BookRental, Integer> {

    @Query("SELECT o FROM BookRental o " +
            "WHERE  (lower(o.bookUnit.signature) LIKE %:query%) " +
            "AND (" +
            "       (o.dateOfRental >= :orderDateFrom OR :orderDateFrom = null) " +
            "   AND (o.dateOfRental <= :orderDateTo OR :orderDateTo = null)" +
            ")")
    List<BookRental> findByQueryAndCreatedAtBetween(@Param("query") String query,
                                                    @Param("orderDateFrom") Date orderDateFrom,
                                                    @Param("orderDateTo") Date orderDateTo);

    List<BookRental> findAll();

    BookRental findBybookUnit(UUID signature);

    List<BookRental> findByBookOrderUnitId(int id);

    @Override
    <S extends BookRental> S save(S entity);
}
