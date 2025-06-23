package com.jv.forum_api.dto.comments;

import com.jv.forum_api.dto.users.UserSimpleResponse;

public record PostCommentSimple(
        Integer commentId,
        UserSimpleResponse createdBy
) {}
