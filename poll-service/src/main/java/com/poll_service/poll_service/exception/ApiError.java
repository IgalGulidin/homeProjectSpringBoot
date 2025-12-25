package com.poll_service.poll_service.exception;

import java.time.Instant;

public class ApiError {
    private int status;
    private String message;
    private Instant timeStamp;

    public ApiError(int status, String message, Instant timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public static ApiError of(int status, String message) {
        return new ApiError(status, message, Instant.now());
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public Instant getTimeStamp() {
        return this.timeStamp;
    }
}
