package com.jv.forum_api.security;

import com.jv.forum_api.repository.UserRepository;
import com.jv.forumapi.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component("auditorAwareImpl")
public class SpringSecurityAuditorAware implements AuditorAware<User> {

    private UserRepository userRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername());

        return Optional.ofNullable(user);

    }
}
