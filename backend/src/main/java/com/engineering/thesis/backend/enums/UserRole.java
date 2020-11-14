package com.engineering.thesis.backend.enums;

import javassist.NotFoundException;
import lombok.Getter;

@Getter
public enum UserRole {
    DOCTOR("ROLE_DOCTOR"),
    PATIENT("ROLE_PATIENT");

    private final String role;
    UserRole(String role) {
        this.role = role;
    }
    public static UserRole parse(String role){
        for(UserRole userRole : UserRole.values()){
            if (userRole.name().equals(role)){
                return userRole;
            }
        }
        throw new RuntimeException("Role :" + role + "doesn't exist.");
    }
}
