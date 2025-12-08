package com.poll_service.poll_service.exception;

public class PollNotFoundException extends RuntimeException{

    public PollNotFoundException(String message) {
        super(message);
    }
}
