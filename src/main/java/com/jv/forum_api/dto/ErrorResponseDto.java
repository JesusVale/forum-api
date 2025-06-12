package com.jv.forum_api.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponseDto(
        String message,
        HttpStatus status
) {}
