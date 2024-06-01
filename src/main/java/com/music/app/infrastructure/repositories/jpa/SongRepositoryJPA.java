package com.music.app.infrastructure.repositories.jpa;

import com.music.app.infrastructure.repositories.entities.SongEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepositoryJPA extends PagingAndSortingRepository<SongEntity, String>, JpaRepository<SongEntity, String> {
    Optional<SongEntity> findByTitle(String name);

    List<SongEntity> findByIsPublicTrueOrCreatedByEmail(String email, Pageable pageable);
}
