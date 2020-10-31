package com.engineering.thesis.backend.config;

import com.engineering.thesis.backend.JwtFilter;
import com.engineering.thesis.backend.serviceImpl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("api").authenticated()
                .antMatchers("api/patientMedicalData").hasAnyRole("DOCTOR", "PATIENT")
                .antMatchers("api/price").hasAnyRole("DOCTOR", "PATIENT")
                .antMatchers("api/patient").hasAnyRole("DOCTOR", "PATIENT")
                .antMatchers("api/appointment").hasAnyRole("DOCTOR", "PATIENT")
                .antMatchers("api/doctor").hasAnyRole("DOCTOR", "PATIENT")
                .and()
                .addFilter(new JwtFilter(authenticationManager()));
    }
}