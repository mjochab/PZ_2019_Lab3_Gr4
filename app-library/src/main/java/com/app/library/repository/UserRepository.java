package com.app.library.repository;

import com.app.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // example method to fetch user by pesel
    User findByPesel(String pesel);

    // example query to fetch users with employee role
    @Query("SELECT user " +
            "FROM User user " +
            "INNER JOIN Role role ON user.role.id = role.id " +
            "WHERE role.name = 'ROLE_EMPLOYEE'")
    List<User> findEmployees();
}
