package com.jv.forum_api.dto.posts;

import com.jv.forum_api.dto.users.UserSimpleResponse;
import com.jv.forumapi.enums.Tag;

import java.time.LocalDateTime;
import java.util.Set;

public record PostResponse(
        Integer postId,
        String title,
        String content,
        UserSimpleResponse createdBy,
        LocalDateTime createdAt,
        Set<Tag> tags
) {
}
