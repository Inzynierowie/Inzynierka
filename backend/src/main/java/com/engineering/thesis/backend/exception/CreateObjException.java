package com.engineering.thesis.backend.exception;

public class CreateObjException extends RuntimeException {
    public CreateObjException(String message) {
        super(message);
    }

    public CreateObjException(String message, Throwable cause) {
        super(message, cause);
    }
}