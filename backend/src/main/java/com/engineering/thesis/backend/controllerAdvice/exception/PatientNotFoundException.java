package com.engineering.thesis.backend.controllerAdvice.exception;

public class PatientNotFoundException extends RuntimeException {
    PatientNotFoundException (Long id){
        super("Could not find patient " + id);
    }
}
