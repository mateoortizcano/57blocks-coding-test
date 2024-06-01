package com.music.app.infrastructure.wrappers;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "User Account", description = "Data of a user")
public record UserAccountDataWrapper(
        @NotNull
        @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        @Schema(description = "Email")
        String email,
        @NotNull
        @Size(min = 10, max = 50)
        @Schema(description = "Password")
        String password) {
}
