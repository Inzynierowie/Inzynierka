package com.engineering.thesis.backend.serviceImpl.user;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.repository.UserRepository;
import com.engineering.thesis.backend.service.DoctorService;
import com.engineering.thesis.backend.service.PatientService;
import com.engineering.thesis.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @Override
    public ResponseEntity<User> findById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id : " + id));
        return ResponseEntity.ok().body(user);
    }

    @Override
    public void deactivateAccountById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id : " + id));

        user.setActive(false);
        userRepository.save(user);
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
