package com.jv.forum_api.dto.posts;

import com.jv.forum_api.dto.users.UserSimpleResponse;
import com.jv.forumapi.enums.Tag;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Set;

public record PostResponse(
        @Schema(
                description = "Identifier of the Post",
                example = "5"
        )
        Integer postId,
        @Schema(
                description = "Title of the Post",
                example = "Marvel Battle"
        )
        String title,
        @Schema(
                description = "Text content of the Post",
                example = "Who do you think is stronger Spiderman or Iron Man?"
        )
        String content,
        @Schema(
                description = "Information of the user that created the post"
        )
        UserSimpleResponse createdBy,
        @Schema(
                description = "Date the post was created",
                example = "2025-09-01T14:30:00"
        )
        LocalDateTime createdAt,
        @Schema(
                description = "Tags assigned to the post"
        )
        Set<Tag> tags
) {
}
