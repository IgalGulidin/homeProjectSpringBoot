package com.poll_service.poll_service.exception;

import feign.FeignException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.of(404, ex.getMessage()));
    }

    @ExceptionHandler(PollNotFoundException.class)
    public ResponseEntity<ApiError> handlePollNotFound(PollNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.of(404, ex.getMessage()));
    }

    @ExceptionHandler(InvalidOptionException.class)
    public ResponseEntity<ApiError> handleInvalidOption(InvalidOptionException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiError.of(400, ex.getMessage()));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiError> handleFeign(FeignException ex) {
        if (ex.status() == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.of(404, "User does not exist"));
        }

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ApiError.of(502, "Upstream service error: " + ex.status()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAnyException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiError.of(500, "Unexpected error"));
    }

    @ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
    public ResponseEntity<ApiError> handleDuplicate(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiError.of(409, "User already answered this poll"));
    }
}
