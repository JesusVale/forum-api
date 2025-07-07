package com.jv.forum_api.dto.posts;

import com.jv.forumapi.enums.Tag;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public record PostSave(
        @NotBlank(message = "Title is required") String title,
        @NotBlank(message = "Content is required") String content,
        Set<Tag> tags
) {
}
