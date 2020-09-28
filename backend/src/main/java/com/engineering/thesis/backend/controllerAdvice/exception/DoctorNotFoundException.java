package com.engineering.thesis.backend.controllerAdvice.exception;

public class DoctorNotFoundException extends RuntimeException{
    DoctorNotFoundException(Long id){
        super("Could not find doctor " + id);
    }
}
