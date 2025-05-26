package com.jv.forum_api.service.interfaces;

import com.jv.forum_api.dto.UserCreate;
import com.jv.forum_api.dto.UserSimpleResponse;

public interface IUserService {

    public UserSimpleResponse save(UserCreate user);

    public String login(UserCreate user);


}
