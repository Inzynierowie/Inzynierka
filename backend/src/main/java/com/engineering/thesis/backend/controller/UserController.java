package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.request.LoginRequest;
import com.engineering.thesis.backend.request.RegisterRequest;
import com.engineering.thesis.backend.service.UserService;
import com.engineering.thesis.backend.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest data) {
        return userService.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest signUpRequest) {
        return userService.register(signUpRequest);
    }

    @GetMapping("/api/users")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return userService.getUserById(id);
    }

    @DeleteMapping("/api/users/{id}")
    public Map<String, Boolean> deleteUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return userService.deleteUserById(id);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }
}