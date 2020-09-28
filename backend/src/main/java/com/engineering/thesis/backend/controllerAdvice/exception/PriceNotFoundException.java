package com.engineering.thesis.backend.controllerAdvice.exception;

public class PriceNotFoundException extends  RuntimeException{
    PriceNotFoundException(Long id){
        super("Could not find price " + id);
    }
}
