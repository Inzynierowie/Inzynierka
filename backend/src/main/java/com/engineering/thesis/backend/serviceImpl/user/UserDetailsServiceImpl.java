package com.engineering.thesis.backend.serviceImpl.user;

import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        User user = userOptional.get();
        user.setRole("ROLE_" + user.getRole());
        return user;
    }
}