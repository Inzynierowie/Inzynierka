package com.engineering.thesis.backend.controllerAdvice.exception;

public class MedicalFacilityNotFoundException extends RuntimeException {
    MedicalFacilityNotFoundException(Long id){
        super("Could not find medical facility " + id);
    }
}
