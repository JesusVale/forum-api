package com.jv.forum_api.mapper;

import com.jv.forum_api.dto.users.UserCreate;
import com.jv.forum_api.dto.users.UserSimpleResponse;
import com.jv.forumapi.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToEntity(UserCreate userCreate) {
        User user = new User();

        user.setUsername(userCreate.username());
        user.setPassword(userCreate.password());
        user.setEmail(userCreate.email());

        return user;
    }

    public UserSimpleResponse mapToSimpleResponse(User user) {

        return new UserSimpleResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail()
        );

    }

}
