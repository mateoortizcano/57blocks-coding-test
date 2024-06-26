package com.music.app.infrastructure.repositories;

import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.model.Song;
import com.music.app.domain.ports.ISongRepository;
import com.music.app.infrastructure.converters.SongConverter;
import com.music.app.infrastructure.repositories.entities.SongEntity;
import com.music.app.infrastructure.repositories.jpa.SongRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository implements ISongRepository {
    @Autowired
    private SongRepositoryJPA songRepositoryJPA;

    @Override
    public void create(Song song) {
        SongEntity songEntity = SongConverter.convertToEntity(song);
        this.songRepositoryJPA.save(songEntity);
    }

    @Override
    public Optional<SongDto> findByTitle(String title) {
        return SongConverter.convertToDto(
                this.songRepositoryJPA.findByTitle(title)
        );
    }

    @Override
    public List<SongDto> getAllPublicSongs(int pageNumber, int pageSize) {
        Pageable paginationLimit = PageRequest.of(pageNumber, pageSize);
        return this.songRepositoryJPA.findByIsPublicTrue(paginationLimit)
                .stream().map(SongConverter::convertToDto).toList();
    }

    @Override
    public List<SongDto> getAllMySongs(String userId, int pageNumber, int pageSize) {
        Pageable paginationLimit = PageRequest.of(pageNumber, pageSize);
        return this.songRepositoryJPA.findByCreatedById(userId, paginationLimit)
                .stream().map(SongConverter::convertToDto).toList();
    }

    @Override
    public void updateSongAttributes(SongDto song) {
        SongEntity songEntity = SongConverter.convertToEntity(song);
        this.songRepositoryJPA.save(songEntity);
    }

    @Override
    public Optional<SongDto> findById(String songId) {
        return SongConverter.convertToDto(this.songRepositoryJPA.findById(songId));
    }
}
