package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.config.jwt.JwtResponse;
import com.engineering.thesis.backend.config.jwt.JwtToken;
import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.repository.DoctorRepository;
import com.engineering.thesis.backend.repository.PatientRepository;
import com.engineering.thesis.backend.repository.UserRepository;
import com.engineering.thesis.backend.request.LoginRequest;
import com.engineering.thesis.backend.request.RegisterRequest;
import com.engineering.thesis.backend.service.UserService;
import com.engineering.thesis.backend.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtToken;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    @Override
    public ResponseEntity<?> register(RegisterRequest signUpRequest) {
        User user = new User();
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        String selectedRole = signUpRequest.getRole();
        if (!selectedRole.isBlank()) {
            if (selectedRole.equals("DOCTOR")) {
                user.setRole("ROLE_DOCTOR");
                Doctor doctor = new Doctor();
                doctor.setUser(user);
                doctorRepository.save(doctor);
            } else {
                user.setRole("ROLE_PATIENT");
                Patient patient = new Patient();
                patient.setUser(user);
                patient.setInsured(false);
                patientRepository.save(patient);
            }
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Error: You should choose one of the roles!");
        }

        user.setName(signUpRequest.getName());
        user.setSurname(signUpRequest.getSurname());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setActive(true);
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @Override
    public ResponseEntity<?> login(LoginRequest data) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        data.getEmail(),
                        data.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtToken.generateJwtToken(authentication);

        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()));
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) throws ResourceNotFoundException {
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
        Optional<User> userInfo = userRepository.findById(id);

        if (userInfo.isPresent()) {
            return new ResponseEntity<>(userRepository.save(User.builder()
                    .role(userInfo.get().getRole())
                    .email(userInfo.get().getEmail())
                    .name(userInfo.get().getName())
                    .surname(userInfo.get().getSurname())
                    .password(userInfo.get().getPassword())
                    .build()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
