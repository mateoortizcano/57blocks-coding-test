package com.music.app.domain.exceptions;

public class NotFoundExxeption extends RuntimeException {
    private String message;

    public NotFoundExxeption(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
