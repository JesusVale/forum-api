package com.jv.forum_api.dto.comments;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record PostCommentSave(
        @Schema(
                description = "The original post that this comment belongs to"
        )
        @NotEmpty(message = "Root Post is required")
        Integer rootPost,
        @Schema(
                description = "The comment to which this comment is replying, if applicable"
        )
        Integer commentPost,
        @Schema(
                description = "Text content of the comment", example = "I think Marvel is better"
        )
        @NotBlank(message = "Content is required")
        String content
) {
}
