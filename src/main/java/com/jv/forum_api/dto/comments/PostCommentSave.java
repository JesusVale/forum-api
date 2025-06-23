package com.jv.forum_api.dto.comments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record PostCommentSave(
        @NotEmpty(message = "Root Post is required") Integer rootPost,
        Integer commentPost,
        @NotBlank(message = "Content is required") String content
) {
}
