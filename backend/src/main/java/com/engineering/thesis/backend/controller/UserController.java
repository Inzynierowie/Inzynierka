package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deactivateAccountById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        userService.deactivateAccountById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }
}