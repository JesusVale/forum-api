package com.jv.forum_api.service.interfaces;

import com.jv.forum_api.dto.users.UserCreate;
import com.jv.forum_api.dto.users.UserSimpleResponse;
import com.jv.forum_api.dto.users.UserUpdate;

public interface IUserService {

    public UserSimpleResponse save(UserCreate user);

    public String login(UserCreate user);

    public UserSimpleResponse update(Integer id, UserUpdate user);

}
