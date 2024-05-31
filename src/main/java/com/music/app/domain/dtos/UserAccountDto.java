package com.music.app.domain.dtos;

public class UserAccountDto {
    private String email;
    private String password;

    public UserAccountDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
