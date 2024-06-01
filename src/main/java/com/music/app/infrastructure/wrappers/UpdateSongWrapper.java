package com.music.app.infrastructure.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Update Song Data", description = "Data to update a specific Song")
public record UpdateSongWrapper(
        @Schema(description = "Tittle of the song")
        String title,
        @Schema(description = "Genre of the song")
        String genre,
        @Schema(description = "Size in bytes of the song")
        Long size,
        @Schema(description = "Author of the song")
        String author,
        @Schema(description = "Album name")
        String album,
        @JsonProperty("public")
        @Schema(description = "Is the song public?", name = "public")
        Boolean isPublic) {
}
