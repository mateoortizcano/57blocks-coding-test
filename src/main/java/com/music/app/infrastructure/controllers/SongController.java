package com.music.app.infrastructure.controllers;

import com.music.app.application.managers.song.CreateSongManager;
import com.music.app.application.managers.song.ListSongsManager;
import com.music.app.application.managers.song.UpdateSongManager;
import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.model.Song;
import com.music.app.domain.model.UpdateSongData;
import com.music.app.infrastructure.converters.SongConverter;
import com.music.app.infrastructure.wrappers.SongWrapper;
import com.music.app.infrastructure.wrappers.UpdateSongWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
@Tag(name = "Song controller")
public class SongController {
    @Autowired
    private CreateSongManager createSongManager;
    @Autowired
    private ListSongsManager listSongsManager;
    @Autowired
    private UpdateSongManager updateSongManager;


    @Operation(summary = "Create a song", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SongWrapper songWrapper) {
        String requestingUserId = getIdRequestingUser();
        Song song = SongConverter.convertToDomain(songWrapper, requestingUserId);
        createSongManager.execute(song);
    }

    @Operation(summary = "Get all public songs", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public List<SongDto> getAll(@RequestParam(name = "page-number") int pageNumber, @RequestParam(name = "page-size") int pageSize) {
        return this.listSongsManager.getAllPublicSongs(pageNumber, pageSize);
    }

    @Operation(summary = "Get my songs", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/created-by-me")
    public List<SongDto> getAllMySongs(@RequestParam(name = "page-number") int pageNumber, @RequestParam(name = "page-size") int pageSize) {
        String requestingUserId = getIdRequestingUser();
        return this.listSongsManager.getAllMySongs(requestingUserId, pageNumber, pageSize);
    }

    @Operation(summary = "Update song attributes", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/{song-id}")
    public void updateSong(@RequestBody UpdateSongWrapper songWrapper, @PathVariable("song-id") String songId) {
        String requestingUserId = getIdRequestingUser();
        UpdateSongData updateSongData = SongConverter.convertToDomain(songWrapper);
        this.updateSongManager.execute(songId, updateSongData, requestingUserId);
    }

    private String getIdRequestingUser() {
        return ((UserAccountDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).id();
    }
}
