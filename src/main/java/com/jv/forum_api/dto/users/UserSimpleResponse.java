package com.jv.forum_api.dto.users;

public record UserSimpleResponse(
        Integer userId,
        String username,
        String email
) {}
