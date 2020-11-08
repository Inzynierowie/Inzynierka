package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.config.jwt.JwtResponse;
import com.engineering.thesis.backend.config.jwt.JwtToken;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.repository.UserRepository;
import com.engineering.thesis.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtToken;

    @Override
    public ResponseEntity<?> register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        String selectedRole = user.getRole();
        if (!selectedRole.isBlank()) {
            if (selectedRole.equals("DOCTOR")) {
                user.setRole("ROLE_DOCTOR");
            } else {
                user.setRole("ROLE_PATIENT");
            }
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Error: You should choose one of the roles!");
        }

        user.setActive(true);
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @Override
    public ResponseEntity<?> login(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtToken.generateJwtToken(authentication);

        User authenticationPrincipal = (User) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()));
//        return ResponseEntity.ok();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        user.orElseThrow(() -> new UsernameNotFoundException(email + " not found."));
        return user.get();
    }
}
