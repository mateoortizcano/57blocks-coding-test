package com.music.app.domain.ports;

import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.model.Song;

import java.util.List;
import java.util.Optional;

public interface ISongRepository {
    /**
     * Creates a song in the repository
     *
     * @param song The song object
     */
    void create(Song song);

    Optional<SongDto> findByTitle(String name);

    /**
     * Get all the songs which are public plus the songs created by the email provided
     *
     * @param email
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<SongDto> getAllPublicOrCreatedByProvidedEmail(String email, int pageNumber, int pageSize);
}
