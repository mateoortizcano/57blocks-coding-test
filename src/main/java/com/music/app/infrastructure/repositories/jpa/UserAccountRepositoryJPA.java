package com.music.app.infrastructure.repositories.jpa;

import com.music.app.infrastructure.repositories.entities.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepositoryJPA extends JpaRepository<UserAccountEntity, UUID> {
    Optional<UserAccountEntity> findByEmail(String email);
}
