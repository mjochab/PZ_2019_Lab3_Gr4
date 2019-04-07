package com.app.library.repository;

import com.app.library.model.Role;
import com.app.library.model.enumerate.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    // example method to find role by name
    Role findByName(RoleName name);
}
