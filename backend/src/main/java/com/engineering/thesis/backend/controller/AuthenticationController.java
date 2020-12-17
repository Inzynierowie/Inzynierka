package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.config.jwt.JwtToken;
import com.engineering.thesis.backend.model.authentication.LoginRequest;
import com.engineering.thesis.backend.model.authentication.RegisterRequest;
import com.engineering.thesis.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtToken jwtToken;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest data) {
        return authenticationService.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest signUpRequest) {
        return authenticationService.register(signUpRequest);
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<String> refreshToken(HttpServletRequest request) {
        String userEmail = (String) request.getAttribute("userEmail");
        return ResponseEntity.ok(jwtToken.generateRefreshToken(userEmail));
    }
}