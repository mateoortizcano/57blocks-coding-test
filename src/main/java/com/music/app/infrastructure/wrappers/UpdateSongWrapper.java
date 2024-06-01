package com.music.app.infrastructure.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(name = "Update Song Data", description = "Data to update a specific Song")
public record UpdateSongWrapper(
        @Size(min = 1,max = 255)
        @Schema(description = "Tittle of the song")
        String title,
        @Size(min = 1,max = 255)
        @Schema(description = "Genre of the song")
        String genre,
        @Size(min = 1)
        @Schema(description = "Size in bytes of the song")
        Long size,
        @Size(min = 1,max = 255)
        @Schema(description = "Author of the song")
        String author,
        @Size(min = 1,max = 255)
        @Schema(description = "Album name")
        String album,
        @JsonProperty("public")
        @Schema(description = "Is the song public?", name = "public")
        Boolean isPublic) {
}
