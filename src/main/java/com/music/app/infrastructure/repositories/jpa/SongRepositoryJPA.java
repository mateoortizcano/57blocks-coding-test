package com.music.app.infrastructure.repositories.jpa;

import com.music.app.infrastructure.repositories.entities.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongRepositoryJPA extends JpaRepository<SongEntity, String> {
    Optional<SongEntity> findByTitle(String name);
}
