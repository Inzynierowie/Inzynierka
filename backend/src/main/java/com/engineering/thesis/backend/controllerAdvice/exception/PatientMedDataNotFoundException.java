package com.engineering.thesis.backend.controllerAdvice.exception;

public class PatientMedDataNotFoundException extends RuntimeException {
    PatientMedDataNotFoundException(Long id){
        super("Could not find patient Med Data " + id);
    }
}
