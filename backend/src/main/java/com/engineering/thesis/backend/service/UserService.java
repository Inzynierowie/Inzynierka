package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<User> findById(Long id) throws ResourceNotFoundException;

    void deactivateAccountById(Long id) throws ResourceNotFoundException;

    ResponseEntity<User> updateUserById(Long id, User user);

    List<User> findAllUsers();
}
