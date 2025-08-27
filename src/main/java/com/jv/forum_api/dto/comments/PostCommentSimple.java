package com.jv.forum_api.dto.comments;

import com.jv.forum_api.dto.users.UserSimpleResponse;
import io.swagger.v3.oas.annotations.media.Schema;

public record PostCommentSimple(
        @Schema(
                description = "Comment Identifier", example = "2"
        )
        Integer commentId,
        @Schema(
                description = "Information about the user who created the comment"
        )
        UserSimpleResponse createdBy
) {}
