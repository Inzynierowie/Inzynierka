package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.request.LoginRequest;
import com.engineering.thesis.backend.request.RegisterRequest;
import com.engineering.thesis.backend.util.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    ResponseEntity<?> register(RegisterRequest signUpRequest);
    ResponseEntity<?> login(LoginRequest data);
    ResponseEntity<User> getUserById(Long id) throws ResourceNotFoundException;
    Map<String, Boolean> deleteUserById(Long id) throws ResourceNotFoundException;
    ResponseEntity<User> updateUserById(Long id, User user);
    List<User> findAllUsers();
}
