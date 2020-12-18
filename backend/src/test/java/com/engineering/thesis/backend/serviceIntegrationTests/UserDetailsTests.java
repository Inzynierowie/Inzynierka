package com.engineering.thesis.backend.serviceIntegrationTests;

import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.repository.UserRepository;
import com.engineering.thesis.backend.serviceImpl.user.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserDetailsTests {

    @Mock(lenient = true)
    private UserRepository userService;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Test
    void shouldThrowExceptionWhenTryLoadUserByEmail() {
        final User user = new User(1l, "Tom", "Kowalsky", "dsaccdsa@osom.com", "1I@wsdas", "ROLE_DOCTOR", true);
        given(userService.findById(user.getId())).willReturn(Optional.of(user));
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsServiceImpl.loadUserByUsername(user.getEmail());
        });
        verify(userService, never()).save(any(User.class));
    }

}
