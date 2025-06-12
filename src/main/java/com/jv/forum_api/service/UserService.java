package com.jv.forum_api.service;

import com.jv.forum_api.dto.users.UserCreate;
import com.jv.forum_api.dto.users.UserSimpleResponse;
import com.jv.forum_api.dto.users.UserUpdate;
import com.jv.forum_api.mapper.UserMapper;
import com.jv.forum_api.repository.UserRepository;
import com.jv.forum_api.service.interfaces.IUserService;
import com.jv.forumapi.entities.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository repository;
    private BCryptPasswordEncoder encoder;
    private UserMapper mapper;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;

    @Override
    public UserSimpleResponse save(UserCreate user) {
        User userSave = mapper.mapToEntity(user);
        userSave.setPassword(encoder.encode(userSave.getPassword()));
        User userSaved = repository.save(userSave);
        return mapper.mapToSimpleResponse(userSaved);
    }

    @Override
    public String login(UserCreate user) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.username(), user.password()));
        if(auth.isAuthenticated()) {
            return jwtService.generateToken(user.username());
        }

        return null;

    }

    @Override
    public UserSimpleResponse update(Integer id, UserUpdate user) {

        User userDB = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if(user.username() != null) {
            userDB.setUsername(user.username());
        }

        if(user.email() != null) {
            userDB.setEmail(user.email());
        }

        if(user.about() != null) {
            userDB.setAbout(user.about());
        }

        if(user.picture() != null) {
            userDB.setPicture(user.picture());
        }

        User userDBUpdated = repository.save(userDB);

        return mapper.mapToSimpleResponse(userDBUpdated);
    }

}
