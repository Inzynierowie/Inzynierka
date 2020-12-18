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
    @NotBlank(message = "Name can't be empty")
    private String name;
    @NotBlank(message = "Surname can't be empty")
    private String surname;
    @NotBlank(message = "Email can't be empty")
    @Size(max = 50, message = "Email can be up to {max} characters long")
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "Password can't be empty")
    @Size(min = 6, max = 50, message = "Password should be {min} to {max} characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$!%^*?&])[A-Za-z\\d@$!%*?&].*$",
            message = "Password should have at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;
    @NotBlank(message = "Role can't be empty")
    private String role;
}