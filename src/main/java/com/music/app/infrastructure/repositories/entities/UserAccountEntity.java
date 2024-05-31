package com.music.app.infrastructure.repositories.entities;

import com.music.app.infrastructure.StringCryptoConverter;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class UserAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Convert(converter = StringCryptoConverter.class)
    private String password;

    public UserAccountEntity(UUID id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UserAccountEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
