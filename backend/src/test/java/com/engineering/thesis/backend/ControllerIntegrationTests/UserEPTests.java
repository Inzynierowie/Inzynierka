package com.engineering.thesis.backend.ControllerIntegrationTests;

import com.engineering.thesis.backend.config.jwt.JwtToken;
import com.engineering.thesis.backend.config.jwt.UnauthorizedHandler;
import com.engineering.thesis.backend.serviceImpl.PriceServiceImpl;
import com.engineering.thesis.backend.serviceImpl.user.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserEPTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private JwtToken jwtToken;

    @MockBean
    private PriceServiceImpl priceServiceImpl;

    @MockBean
    private UnauthorizedHandler unauthorizedHandler;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void patientShouldNotBeAllowedToDeleteUsers() throws Exception {
        this.mockMvc
                .perform(delete("/api/users/1").with(user("rob").roles("PATIENT")))
                .andExpect(status().isForbidden());
    }
}
