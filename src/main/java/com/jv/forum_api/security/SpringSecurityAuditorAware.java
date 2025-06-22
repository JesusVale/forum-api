package com.jv.forum_api.security;

import com.jv.forum_api.service.interfaces.IAuthService;
import com.jv.forumapi.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component("auditorAwareImpl")
public class SpringSecurityAuditorAware implements AuditorAware<User> {

    private IAuthService authService;

    @Override
    public Optional<User> getCurrentAuditor() {
        return authService.getLoggedUser();
    }
}
