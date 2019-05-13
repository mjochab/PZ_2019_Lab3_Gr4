package com.app.library.repository;

import com.app.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    // example query to fetch users with employee role
    @Query("SELECT user " +
            "FROM User user " +
            "INNER JOIN Role role ON role.id = user.role.id " +
            "WHERE role.name = 'ROLE_EMPLOYEE'")
    List<User> findEmployees();

    @Query("SELECT user " +
            "FROM User user " +
            "INNER JOIN Role role ON role.id = user.role.id " +
            "WHERE role.name = 'ROLE_READER' " +
            "AND (  lower(user.firstName) LIKE %:query% " +
            "OR     lower(user.surname) LIKE %:query% " +
            "OR     lower(user.pesel) LIKE %:query% " +
            "OR     lower(user.email) LIKE %:query%)")
    List<User> findReadersByQuery(@Param("query") String query);
}
