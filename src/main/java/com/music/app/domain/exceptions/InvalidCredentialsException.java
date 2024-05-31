package com.music.app.domain.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    private String message;

    public InvalidCredentialsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
