package com.engineering.thesis.backend.config.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Slf4j
@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        Exception exception = (Exception) request.getAttribute("exception");
        String message;
        if (exception != null) {
            message = exception.getMessage();
        } else {
            if (authenticationException.getCause() != null) {
                message = authenticationException.getMessage();
            } else {
                message = "Error: Unauthorized";
            }
        }
        response.sendError(SC_UNAUTHORIZED, message);
    }
}