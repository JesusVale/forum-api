package com.jv.forum_api.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UserCreate(
        @NotBlank(message = "Username is required")
        String username,
        @Email(message = "Email is not valid")
        String email,
        @NotBlank(message = "Password is required")
        String password
) {}
