package com.jv.forum_api.dto.comments;

import com.jv.forum_api.dto.posts.PostSimple;
import com.jv.forum_api.dto.users.UserSimpleResponse;

import java.time.LocalDateTime;

public record PostCommentResponse(
        Integer commentId,
        PostSimple rootPost,
        PostCommentSimple commentPost,
        String content,
        UserSimpleResponse createdBy,
        LocalDateTime createdAt
) {}
