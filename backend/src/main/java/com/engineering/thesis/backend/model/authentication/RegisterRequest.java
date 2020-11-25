package com.engineering.thesis.backend.model.authentication;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;

    @NotBlank(message = "Email can't be empty")
    @Size(max = 50, message = "Email can be up to 50 characters long")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 6, max = 50, message = "Password should be 6 to 50 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$!%^*?&])[A-Za-z\\d@$!%*?&].*$",
            message = "Password should have at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;

    @NotBlank
    private String role;
}