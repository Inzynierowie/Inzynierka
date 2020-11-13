package com.engineering.thesis.backend.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String role;

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }
}