package com.jv.forum_api.dto.follows;

import com.jv.forum_api.dto.users.UserSimpleResponse;
import io.swagger.v3.oas.annotations.media.Schema;

public record FollowUsersResponse(
        @Schema(
                description = "The user who is followed"
        )
        UserSimpleResponse user,
        @Schema(
                description = "The follower of the user"
        )
        UserSimpleResponse follower
) {
}
