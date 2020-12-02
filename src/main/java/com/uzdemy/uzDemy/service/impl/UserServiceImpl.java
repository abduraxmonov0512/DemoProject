package com.uzdemy.uzDemy.service.impl;

import com.uzdemy.uzDemy.model.Role;
import com.uzdemy.uzDemy.model.User;
import com.uzdemy.uzDemy.repository.RoleRepository;
import com.uzdemy.uzDemy.repository.UserRepository;
import com.uzdemy.uzDemy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User register(User user) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        User registeredUser = userRepository.save(user);
        log.info("IN register - user: {} registered successfully", registeredUser);

        return registeredUser;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

        log.info("IN save user: {} have saved", user);
    }

    @Override
    public List<User> getAll() {
        List<User> list = userRepository.findAll();

        log.info("IN getAll - {} users found", list.size());
        return list;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            log.warn("IN findById user not found with id {}", id);
            return null;
        }

        log.info("IN findById user found : {} with id {}", user, id);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {

            log.info("IN findByEmail user found: {} with email: {}", user, email);
        } else {
            log.info("IN findByEmail user not found with email: {}",  email);
        }

        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete user deleted with id: {}", id);
    }
}
