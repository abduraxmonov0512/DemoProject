package com.uzdemy.uzDemy.service;

import com.uzdemy.uzDemy.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    void save(User user);

    List<User> getAll();

    User findById(Long id);

    User findByEmail(String email);

    void delete(Long id);
}
