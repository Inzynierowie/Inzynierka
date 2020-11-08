package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> register(User user);
    ResponseEntity<?> login(User user);
}
