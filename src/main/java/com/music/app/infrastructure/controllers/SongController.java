package com.music.app.infrastructure.controllers;

import com.music.app.application.managers.song.CreateSongManager;
import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.model.Song;
import com.music.app.domain.ports.ISongRepository;
import com.music.app.infrastructure.converters.SongConverter;
import com.music.app.infrastructure.wrappers.SongWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
@Tag(name = "Song controller")
public class SongController {
    @Autowired
    private CreateSongManager createSongManager;
    @Autowired
    private ISongRepository songRepository;


    @Operation(summary = "Create a song", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SongWrapper songWrapper) {
        UserAccountDto userAccountDto = (UserAccountDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Song song = SongConverter.convertToDomain(songWrapper, userAccountDto.id());
        createSongManager.execute(song);
    }

    @Operation(summary = "Get all songs", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public List<SongDto> getAll(@RequestParam(name = "page-number") int pageNumber, @RequestParam(name = "page-size") int pageSize) {
        UserDetails userAccountDto = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.songRepository.getAllPublicOrCreatedByProvidedEmail(userAccountDto.getUsername(), pageNumber, pageSize);
    }
}
