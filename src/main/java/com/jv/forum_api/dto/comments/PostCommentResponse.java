package com.jv.forum_api.dto.comments;

import com.jv.forum_api.dto.posts.PostSimple;
import com.jv.forum_api.dto.users.UserSimpleResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record PostCommentResponse(
        @Schema(
                description = "Comment Identifier", example = "2"
        )
        Integer commentId,
        @Schema(
                description = "The original post that this comment belongs to"
        )
        PostSimple rootPost,
        @Schema(
                description = "The comment to which this comment is replying, if applicable"
        )
        PostCommentSimple commentPost,
        @Schema(
                description = "Text content of the comment", example = "I think Marvel is better"
        )
        String content,
        @Schema(
                description = "Information about the user who created the comment"
        )
        UserSimpleResponse createdBy,
        @Schema(
                description = "Timestamp when the comment was created",
                example = "2025-08-26T14:30:00"
        )
        LocalDateTime createdAt
) {}
