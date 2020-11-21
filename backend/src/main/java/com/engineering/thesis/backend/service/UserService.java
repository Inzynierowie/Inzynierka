package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    ResponseEntity<User> findUserById(Long id) throws ResourceNotFoundException;
    Map<String, Boolean> deleteUserById(Long id) throws ResourceNotFoundException;
    ResponseEntity<User> updateUserById(Long id, User user);
    List<User> findAllUsers();
}
