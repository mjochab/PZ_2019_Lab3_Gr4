package com.app.library.repository;
import com.app.library.model.EmployeeOfLibrary;

import com.app.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeOfLibraryRepository extends JpaRepository<EmployeeOfLibrary, Integer> {


}
