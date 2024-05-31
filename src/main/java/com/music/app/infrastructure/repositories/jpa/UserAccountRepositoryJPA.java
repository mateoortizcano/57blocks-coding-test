package com.music.app.infrastructure.repositories.jpa;

import com.music.app.infrastructure.repositories.entities.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepositoryJPA extends JpaRepository<UserAccountEntity, String> {
    Optional<UserAccountEntity> findByEmail(String email);
}
