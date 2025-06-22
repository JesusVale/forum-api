package com.jv.forum_api.service.interfaces;

import com.jv.forumapi.entities.User;

import java.util.Optional;

public interface IAuthService {

    public Optional<User> getLoggedUser();

}
