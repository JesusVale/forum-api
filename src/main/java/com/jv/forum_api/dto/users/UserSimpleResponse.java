package com.jv.forum_api.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserSimpleResponse(
        @Schema(
                description = "Identifier of user",
                example = "3"
        )
        Integer userId,
        @Schema(
                example = "Mr. Dev"
        )
        String username,
        @Schema(
                description = "Email of the user", example = "john@gmail.com"
        )
        String email
) {}
