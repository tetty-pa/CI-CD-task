package com.epam.esm.exception;

public class InvalidDataException extends RuntimeException{

    public InvalidDataException() {
        super();
    }

    public InvalidDataException(String message) {
        super(message);
    }

}
