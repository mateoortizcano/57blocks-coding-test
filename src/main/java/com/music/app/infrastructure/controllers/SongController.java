package com.music.app.infrastructure.controllers;

import com.music.app.application.managers.song.CreateSongManager;
import com.music.app.domain.model.Song;
import com.music.app.infrastructure.converters.SongConverter;
import com.music.app.infrastructure.wrappers.SongWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
@Tag(name = "Song controller")
public class SongController {
    @Autowired
    private CreateSongManager createSongManager;

    @Operation(summary = "Create a song")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SongWrapper songWrapper, String emailCreator) {
        Song song = SongConverter.convertToDomain(songWrapper);
        createSongManager.execute(song);
    }
}
