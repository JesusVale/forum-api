package com.jv.forum_api.dto.posts;

import com.jv.forumapi.enums.Tag;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(
        description = "Filters to search a post"
)
public record PostFilter(
        @Schema(
                description = "Filter to search posts by title",
                example = "Marvel"
        )
        String s,
        @Schema(
                description = "Filter to search posts by the user that created the post",
                example = "3"
        )
        Integer userId,
        @Schema(
                description = "Filter to search posts by a period of time they were created. This indicates the start date",
                example = "2025-08-26T14:30:00"
        )
        LocalDateTime startDate,
        @Schema(
                description = "Filter to search posts by a period of time they were created. This indicates the end date",
                example = "2025-09-01T14:30:00"
        )
        LocalDateTime endDate,
        @Schema(
                description = "Filter to search posts by one or multiple tags"
        )
        Set<Tag> tags
) {
}
