package com.music.app.application.managers.song;

import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.services.song.ListSongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListSongsManager {
    @Autowired
    private ListSongsService listSongsService;
    public List<SongDto> getAllPublicSongs(int pageNumber, int pageSize) {
        return listSongsService.getAllPublicSongs(pageNumber, pageSize);
    }

    public List<SongDto> getAllMySongs(String requestingUserId, int pageNumber, int pageSize) {
        return listSongsService.getAllMySongs(requestingUserId, pageNumber, pageSize);
    }
}
