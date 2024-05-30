package com.music.app.domain.exceptions;

public class InvalidValueException extends RuntimeException {
    private String message;

    public InvalidValueException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
