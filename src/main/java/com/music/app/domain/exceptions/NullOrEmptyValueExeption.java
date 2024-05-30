package com.music.app.domain.exceptions;

public class NullOrEmptyValueExeption extends RuntimeException {
    private String message;

    public NullOrEmptyValueExeption(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
