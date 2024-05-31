package com.music.app.domain.ports;

import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.model.Song;

import java.util.Optional;

public interface ISongRepository {
    /**
     * Creates a song in the repository
     *
     * @param song The song object
     */
    void create(Song song);

    Optional<SongDto> findByTitle(String name);
}
