package com.poll_service.poll_service.exception;

public class InvalidOptionException extends RuntimeException{
    public InvalidOptionException(String message) {
        super(message);
    }
}
