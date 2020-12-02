package com.uzdemy.uzDemy.repository;

import com.uzdemy.uzDemy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long > {
    User findByEmail(String email);
}
