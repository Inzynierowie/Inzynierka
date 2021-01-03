package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.config.jwt.JwtToken;
import com.engineering.thesis.backend.enums.UserRole;
import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.model.authentication.JwtResponse;
import com.engineering.thesis.backend.model.authentication.LoginRequest;
import com.engineering.thesis.backend.model.authentication.RegisterRequest;
import com.engineering.thesis.backend.repository.UserRepository;
import com.engineering.thesis.backend.service.AuthenticationService;
import com.engineering.thesis.backend.service.DoctorService;
import com.engineering.thesis.backend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.engineering.thesis.backend.enums.UserRole.DOCTOR;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtToken;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @Transactional
    @Override
    public ResponseEntity<?> register(RegisterRequest signUpRequest) throws ResourceNotFoundException {
        User user = new User();
        if (userRepository.existsByEmailAndIsActive(signUpRequest.getEmail(), true)) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        if (signUpRequest.getRole().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: You should choose one of the roles!");
        }

        UserRole userRole = UserRole.parse(signUpRequest.getRole());
        save(signUpRequest, user, userRole);
        saveRelated(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    private void save(RegisterRequest signUpRequest, User user, UserRole userRole) {
        user.setRole(userRole.name());
        user.setName(signUpRequest.getName());
        user.setSurname(signUpRequest.getSurname());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setActive(true);
        userRepository.save(user);
    }

    private void saveRelated(User user) throws ResourceNotFoundException {
        if (user.getRole().equals(DOCTOR.name())) {
            Doctor doctor = new Doctor();
            doctor.setUser(user);
            doctorService.create(doctor);
        } else {
            Patient patient = new Patient();
            patient.setUser(user);
            patientService.create(patient);
        }
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
        String userRole = "ROLE_" + user.getRole();
        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getEmail(),
                userRole));
    }
}