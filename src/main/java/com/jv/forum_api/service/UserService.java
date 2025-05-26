package com.jv.forum_api.service;

import com.jv.forum_api.dto.UserCreate;
import com.jv.forum_api.dto.UserSimpleResponse;
import com.jv.forum_api.mapper.UserMapper;
import com.jv.forum_api.repository.UserRepository;
import com.jv.forum_api.service.interfaces.IUserService;
import com.jv.forumapi.entities.User;
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
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(auth.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }

        return null;

    }

}
