package com.engineering.thesis.backend.ControllerIntegrationTests;

import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

public class SecurityMockMvcRequestPostProcessors {

    public static RequestPostProcessor Doctor() {
        return user("rob").roles("DOCTOR");
    }

    public static RequestPostProcessor Patient() {
        return user("rob").roles("PATIENT");
    }

    public static RequestPostProcessor InvalidRole() {
        return user("rob").roles("InvalidRole");
    }
}
