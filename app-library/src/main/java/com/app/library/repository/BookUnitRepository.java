package com.app.library.repository;


import com.app.library.model.BookUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookUnitRepository extends JpaRepository<BookUnit, Integer> {

    List<BookUnit> findAll();


    List<BookUnit> findByBookId(int book_id);

    BookUnit findBySignature(String signature);

}
