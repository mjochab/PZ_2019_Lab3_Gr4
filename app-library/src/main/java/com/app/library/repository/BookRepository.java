package com.app.library.repository;


import com.app.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

      List<Book> findAll();


}
