package com.music.app.infrastructure.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "Song", description = "Data of a Song")
public record SongWrapper(
        @NotNull
        @Size(min = 1,max = 255)
        @Schema(description = "Tittle of the song")
        String title,
        @Size(min = 1,max = 255)
        @Schema(description = "Genre of the song")
        String genre,
        @NotNull
        @Size(min = 1)
        @Schema(description = "Size in bytes of the song")
        long size,
        @Size(min = 1,max = 255)
        @NotNull
        @Schema(description = "Author of the song")
        String author,
        @Size(min = 1,max = 255)
        @Schema(description = "Album name")
        String album,
        @JsonProperty("public")
        @NotNull
        @Schema(description = "Is the song public?", name = "public")
        boolean isPublic) {
}
