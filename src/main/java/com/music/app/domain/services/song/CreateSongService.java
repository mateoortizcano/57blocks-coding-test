package com.music.app.domain.services.song;

import com.music.app.domain.model.Song;
import com.music.app.domain.ports.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateSongService {
    @Autowired
    private ISongRepository songRepository;

    public void execute(Song song) {
        this.songRepository.create(song);
    }
}
