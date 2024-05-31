package com.music.app.infrastructure.repositories.entities;

import com.music.app.infrastructure.StringCryptoConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Table(name = "users")
@Entity
public class UserAccountEntity {
    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false)
    private String email;

    @Convert(converter = StringCryptoConverter.class)
    private String password;

    public UserAccountEntity(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UserAccountEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
