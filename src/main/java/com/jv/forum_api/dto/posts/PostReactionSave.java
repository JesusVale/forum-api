package com.jv.forum_api.dto.posts;
import com.jv.forumapi.enums.Reaction;

public record PostReactionSave(
        Integer post,
        Reaction reaction
) {
}
