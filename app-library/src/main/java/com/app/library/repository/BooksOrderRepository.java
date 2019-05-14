package com.app.library.repository;

import com.app.library.model.BooksOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BooksOrderRepository extends JpaRepository<BooksOrder, Integer> {

    @Query("SELECT o FROM BooksOrder o " +
            "INNER JOIN User user ON user.id = o.reader.id " +
            "WHERE  (lower(user.firstName) LIKE %:query% OR lower(user.surname) LIKE %:query%) " +
            "AND (" +
            "       (o.createdAt >= :orderDateFrom OR :orderDateFrom = null) " +
            "   AND (o.createdAt <= :orderDateTo OR :orderDateTo = null)" +
            ")")
    List<BooksOrder> findByQueryAndCreatedAtBetween(@Param("query") String query,
                                                    @Param("orderDateFrom") Date orderDateFrom,
                                                    @Param("orderDateTo") Date orderDateTo);

    List<BooksOrder> findByReaderId(int id);

    BooksOrder findById(int id);
}
