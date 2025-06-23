package com.jv.forum_api.dto.follows;

import com.jv.forum_api.dto.users.UserSimpleResponse;

public record FollowUsersResponse(
        UserSimpleResponse user, //The user who is followed
        UserSimpleResponse follower
) {
}
