package com.music.app.infrastructure.repositories.jpa;

import com.music.app.infrastructure.repositories.entities.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAccountRepositoryJPA extends JpaRepository<UserAccountEntity, UUID> {
    UserAccountEntity findByEmail(String email);
}
