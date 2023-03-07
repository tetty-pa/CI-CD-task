package com.epam.esm.exception;

public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException() {
    }

    public InvalidJwtException(String message) {
        super(message);
    }
}
