package com.engineering.thesis.backend.controllerAdvice.exception;

public class AppointmentNotFoundException extends RuntimeException{
    AppointmentNotFoundException(Long id){
        super("Could not find appointment " + id);
    }
}
