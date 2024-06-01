package com.music.app.application.managers.song;

import com.music.app.domain.model.Song;
import com.music.app.domain.services.song.CreateSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CreateSongManager {
    @Autowired
    private CreateSongService createSongService;
    public void execute(Song song) {
        this.createSongService.execute(song);
    }
}
