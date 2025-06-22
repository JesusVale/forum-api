package com.jv.forum_api.service;

import com.jv.forum_api.repository.UserRepository;
import com.jv.forum_api.service.interfaces.IAuthService;
import com.jv.forumapi.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthService implements IAuthService {

    private UserRepository userRepository;

    @Override
    public Optional<User> getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername());

        return Optional.ofNullable(user);
    }

}
