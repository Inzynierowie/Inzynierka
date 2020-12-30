package com.engineering.thesis.backend.exception;

public class DeleteObjException extends RuntimeException {
    public DeleteObjException(String message) {
        super(message);
    }

    public DeleteObjException(String message, Throwable cause) {
        super(message, cause);
    }
}
