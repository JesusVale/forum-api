package com.jv.forum_api.dto.posts;

import com.jv.forum_api.dto.users.UserSimpleResponse;
import io.swagger.v3.oas.annotations.media.Schema;

public record PostSimple(
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
                description = "Information of the user that created the post"
        )
        UserSimpleResponse createdBy
) {
}
