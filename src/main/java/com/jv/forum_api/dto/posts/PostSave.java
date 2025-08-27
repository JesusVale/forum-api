package com.jv.forum_api.dto.posts;

import com.jv.forumapi.enums.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public record PostSave(
        @NotBlank(message = "Title is required")
        @Schema(
                description = "Title of the Post",
                example = "Marvel Battle"
        )
        String title,
        @NotBlank(message = "Content is required")
        @Schema(
                description = "Text content of the Post",
                example = "Who do you think is stronger Spiderman or Iron Man?"
        )
        String content,
        @Schema(
                description = "Tags assigned to the post"
        )
        Set<Tag> tags
) {
}
