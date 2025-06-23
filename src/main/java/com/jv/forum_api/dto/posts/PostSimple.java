package com.jv.forum_api.dto.posts;

import com.jv.forum_api.dto.users.UserSimpleResponse;

public record PostSimple(
        Integer postId,
        String title,
        UserSimpleResponse createdBy
) {
}
