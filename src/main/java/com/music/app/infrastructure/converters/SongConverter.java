package com.music.app.infrastructure.converters;

import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.model.Song;
import com.music.app.infrastructure.repositories.entities.SongEntity;
import com.music.app.infrastructure.repositories.entities.UserAccountEntity;
import com.music.app.infrastructure.wrappers.SongWrapper;

import java.util.Optional;

public class SongConverter {
    public static Song convertToDomain(SongWrapper song, String idCreator) {
        return new Song(
                song.title(),
                song.genre(),
                song.size(),
                song.author(),
                song.album(),
                song.isPublic(),
                idCreator
        );
    }

    public static SongEntity dtoToEntity(Song song) {
        SongEntity songEntity = new SongEntity();
        songEntity.setTitle(song.title());
        songEntity.setGenre(song.genre());
        songEntity.setSize(song.size());
        songEntity.setAuthor(song.author());
        songEntity.setAlbum(song.album());
        songEntity.setPublic(song.isPublic());
        UserAccountEntity createdBy = new UserAccountEntity();
        createdBy.setId(song.idCreator());
        songEntity.setCreatedBy(createdBy);
        return songEntity;
    }

    public static Optional<SongDto> convertToDto(Optional<SongEntity> entity) {
        return entity.map(
                song -> new SongDto(song.getId(), song.getTitle(), song.getGenre(), song.getSize(), song.getAuthor(),
                        song.getAlbum(), song.getPublic())
        );
    }

    public static SongDto convertToDto(SongEntity entity) {
        return new SongDto(entity.getId(), entity.getTitle(), entity.getGenre(), entity.getSize(), entity.getAuthor(),
                entity.getAlbum(), entity.getPublic());
    }
}
