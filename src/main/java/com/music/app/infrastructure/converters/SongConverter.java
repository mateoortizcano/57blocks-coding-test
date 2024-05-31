package com.music.app.infrastructure.converters;

import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.model.Song;
import com.music.app.infrastructure.repositories.entities.SongEntity;
import com.music.app.infrastructure.wrappers.SongWrapper;

import java.util.Optional;

public class SongConverter {
    public static Song convertToDomain(SongWrapper song) {
        return new Song(
                song.getTitle(),
                song.getGenre(),
                song.getSize(),
                song.getAuthor(),
                song.getAlbum(),
                song.isPublic()
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
        return songEntity;
    }

    public static Optional<SongDto> convertToDto(Optional<SongEntity> entity) {
        return entity.map(
                song -> new SongDto(song.getId(), song.getTitle(), song.getGenre(), song.getSize(), song.getAuthor(),
                        song.getAlbum(), song.getPublic())
        );
    }
}
