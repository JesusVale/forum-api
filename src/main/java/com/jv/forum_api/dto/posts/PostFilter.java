package com.jv.forum_api.dto.posts;

import com.jv.forumapi.enums.Tag;

import java.time.LocalDateTime;
import java.util.Set;

public record PostFilter(
        String s,
        Integer userId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Set<Tag> tags
) {
}
