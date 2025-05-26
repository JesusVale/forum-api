package com.jv.forum_api.mapper;

import com.jv.forum_api.dto.UserCreate;
import com.jv.forum_api.dto.UserSimpleResponse;
import com.jv.forumapi.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToEntity(UserCreate userCreate) {
        User user = new User();

        user.setUsername(userCreate.getUsername());
        user.setPassword(userCreate.getPassword());
        user.setEmail(userCreate.getEmail());

        return user;
    }

    public UserSimpleResponse mapToSimpleResponse(User user) {

        UserSimpleResponse userSimpleResponse = new UserSimpleResponse();
        userSimpleResponse.setUsername(user.getUsername());
        userSimpleResponse.setEmail(user.getEmail());
        userSimpleResponse.setUserId(user.getUserId());

        return userSimpleResponse;

    }

}
