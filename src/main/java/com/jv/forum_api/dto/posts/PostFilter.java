package com.jv.forum_api.dto.posts;

import java.time.LocalDateTime;

public record PostFilter(
        String s,
        Integer userId,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
