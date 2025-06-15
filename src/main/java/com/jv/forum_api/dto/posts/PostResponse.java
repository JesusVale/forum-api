package com.jv.forum_api.dto.posts;

import com.jv.forum_api.dto.users.UserSimpleResponse;

import java.time.LocalDateTime;

public record PostResponse(
        Integer postId,
        String title,
        String content,
        UserSimpleResponse createdBy,
        LocalDateTime createdAt
) {
}
