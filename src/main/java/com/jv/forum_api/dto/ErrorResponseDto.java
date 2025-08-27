package com.jv.forum_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

public record ErrorResponseDto(
        @Schema(
                description = "Error Message", example = "Email is not valid"
        )
        String message,
        @Schema(
                description = "Status of the error", example = "500"
        )
        HttpStatus status
) {}
