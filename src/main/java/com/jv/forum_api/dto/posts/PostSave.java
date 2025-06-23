package com.jv.forum_api.dto.posts;

import jakarta.validation.constraints.NotBlank;

public record PostSave(
        @NotBlank(message = "Title is required") String title,
        @NotBlank(message = "Content is required") String content
) {
}
