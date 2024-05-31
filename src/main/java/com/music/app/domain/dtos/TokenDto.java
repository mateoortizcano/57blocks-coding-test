package com.music.app.domain.dtos;

public class TokenDto {
    private String token;
    private long expiresIn;

    public TokenDto(String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }
}
