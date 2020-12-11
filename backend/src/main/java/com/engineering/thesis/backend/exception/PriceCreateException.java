package com.engineering.thesis.backend.exception;

public class PriceCreateException extends RuntimeException {
    public PriceCreateException(String message) {
        super(message);
    }

    public PriceCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}