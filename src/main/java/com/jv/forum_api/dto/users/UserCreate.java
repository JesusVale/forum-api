package com.jv.forum_api.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreate(
        @NotBlank(message = "Username is required")
        @Schema(
                example = "Mr. Dev"
        )
        String username,
        @Email(message = "Email is not valid")
        @Schema(
                description = "Email of the user", example = "john@gmail.com"
        )
        String email,
        @NotBlank(message = "Password is required")
        @Schema(
                description = "Password of the user"
        )
        String password
) {}
