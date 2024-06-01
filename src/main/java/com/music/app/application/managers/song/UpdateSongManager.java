package com.music.app.application.managers.song;

import com.music.app.domain.model.UpdateSongData;
import com.music.app.domain.services.song.UpdateSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UpdateSongManager {

    @Autowired
    private UpdateSongService updateSongService;

    public void execute(String songId, UpdateSongData song, String idRequestingUser) {
        this.updateSongService.execute(songId, song, idRequestingUser);
    }
}
