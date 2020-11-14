package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.authentication.LoginRequest;
import com.engineering.thesis.backend.model.authentication.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> register(RegisterRequest signUpRequest);
    ResponseEntity<?> login(LoginRequest data);
}
