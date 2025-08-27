package com.jv.forum_api.dto.posts;
import com.jv.forumapi.enums.Reaction;
import io.swagger.v3.oas.annotations.media.Schema;

public record PostReactionSave(
        @Schema(
                description = "Identifier of the post that will have the reaction",
                example = "5"
        )
        Integer post,
        @Schema(
                description = "Reaction assigned to the post"
        )
        Reaction reaction
) {
}
