package com.music.app.infrastructure.wrappers;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "Song", description = "Data of a Song")
public record SongWrapper(
        @NotNull
        @Schema(description = "Tittle of the song")
        String title,
        @Schema(description = "Genre of the song")
        String genre,
        @NotNull
        @Size(min = 1)
        @Schema(description = "Size in bytes of the song")
        long size,
        @NotNull
        @Schema(description = "Author of the song")
        String author,
        @Schema(description = "Album name")
        String album,
        @NotNull
        @Schema(description = "Is the song public?")
        boolean isPublic) {
}
