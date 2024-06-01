package com.music.app.infrastructure.wrappers;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Token", description = "Data of the returned token when authenticates successfully")
public record TokenWrapper(
        @NotNull
        @Schema(description = "The token")
        String token,
        @NotNull
        @Schema(description = "The expiration in ms from the generation")
        long expiresIn) {
}
