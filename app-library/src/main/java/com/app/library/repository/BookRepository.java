package com.app.library.repository;


import com.app.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT book " +
            "FROM Book book " +
            "WHERE    lower(book.name) LIKE %:query% " +
            "OR       lower(book.author) LIKE %:query% " +
            "OR       lower(book.publishingCompany) LIKE %:query%")
    List<Book> findByQuery(@Param("query") String query);

    Book findByName(String name);

}
