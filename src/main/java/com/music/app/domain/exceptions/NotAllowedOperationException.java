package com.music.app.domain.exceptions;

public class NotAllowedOperationException extends RuntimeException {
    private String message;

    public NotAllowedOperationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
