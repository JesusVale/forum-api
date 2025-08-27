package com.jv.forum_api.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.URL;

public record UserUpdate(
        @Schema(
                example = "Mr. Dev"
        )
        String username,
        @Email(message = "Email is not valid")
        @Schema(
                description = "Email of the user", example = "john@gmail.com"
        )
        String email,
        @URL(message = "Picture not valid")
        @Schema(
                description = "Optional picture of the user"
        )
        String picture,
        @Schema(
                description = "Optional description of the user",
                example = "Hi! I am John, i like music and comics, i hope you like my posts"
        )
        String about
) {}
