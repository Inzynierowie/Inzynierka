package com.engineering.thesis.backend.serviceImpl.user;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.repository.UserRepository;
import com.engineering.thesis.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<User> findUserById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id : " + id));
        return ResponseEntity.ok().body(user);
    }

    @Override
    public Map<String, Boolean> deleteUserById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id : " + id));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @Override
    public ResponseEntity<User> updateUserById(Long id, User user) {
        Optional<User> userDb = userRepository.findById(id);

        if (userDb.isPresent()) {
            return new ResponseEntity<>(userRepository.save(User.builder()
                    .id(id)
                    .role(user.getRole())
                    .email(user.getEmail())
                    .name(user.getName())
                    .surname(user.getSurname())
                    .password(user.getPassword())
                    .build()), OK);
        } else {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
