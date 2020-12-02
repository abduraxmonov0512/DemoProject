package com.uzdemy.uzDemy.repository;

import com.uzdemy.uzDemy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
