package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.repository.UserRepository;
import com.engineering.thesis.backend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String selectedRole = user.getRole();
        if (selectedRole != null) {
            if (selectedRole.equals("DOCTOR")) {
                user.setRole("ROLE_DOCTOR");
            } else {
                user.setRole("ROLE_PATIENT");
            }
        }
        userRepository.save(user);
    }
}
