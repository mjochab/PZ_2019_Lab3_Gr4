package com.app.library.repository;


import com.app.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

      @Query("SELECT book " +
              "FROM Book book " +
              "WHERE    book.name LIKE %:query% " +
              "OR       book.author LIKE %:query% " +
              "OR       book.publishingCompany LIKE %:query%")
      List<Book> findByQuery(@Param("query") String query);
}
