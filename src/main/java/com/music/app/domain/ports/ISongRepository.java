package com.music.app.domain.ports;

import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.model.Song;
import com.music.app.domain.model.UpdateSongData;

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
     * Get all the songs which are public
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<SongDto> getAllPublicSongs(int pageNumber, int pageSize);

    /**
     * Get all the songs created by the email provided
     *
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<SongDto> getAllMySongs(String userId, int pageNumber, int pageSize);

    /**
     * Update a specific song with the not null values
     *
     * @param song
     */
    void updateSongAttributes(SongDto song);

    /**
     * Find a song by its Id
     *
     * @param songId
     * @return
     */
    Optional<SongDto> findById(String songId);
}
