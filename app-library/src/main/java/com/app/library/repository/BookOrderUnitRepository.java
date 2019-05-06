
package com.app.library.repository;

import com.app.library.model.BookOrderUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOrderUnitRepository extends JpaRepository<BookOrderUnit, Integer> {

}
