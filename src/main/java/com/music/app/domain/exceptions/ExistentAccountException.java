package com.music.app.domain.exceptions;

public class ExistentAccountException extends RuntimeException {
    private String message;

    public ExistentAccountException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
