package com.jv.forum_api.service;

import com.jv.forum_api.dto.follows.FollowUsersResponse;
import com.jv.forum_api.events.FollowUserEvent;
import com.jv.forum_api.repository.FollowUserRepository;
import com.jv.forum_api.repository.UserRepository;
import com.jv.forum_api.service.interfaces.IFollowUserService;
import com.jv.forumapi.entities.FollowUsers;
import com.jv.forumapi.entities.FollowUsersId;
import com.jv.forumapi.entities.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class FollowUserService implements IFollowUserService {

    private FollowUserRepository followUserRepository;
    private UserRepository userRepository;

    private AuthService authService;

    private ApplicationEventPublisher eventPublisher;

    @Override
    public FollowUsers save(Integer userFollowing) {

        Optional<User> userOptional = authService.getLoggedUser();

        if(userOptional.isEmpty()) {
            throw new EntityNotFoundException("Authentication error");
        }

        if(!userRepository.existsByUserId(userFollowing)) {
            throw new EntityNotFoundException("User not found");
        }

        FollowUsers followUser = new FollowUsers();
        FollowUsersId followUsersId = new FollowUsersId();

        User userFollow = new User();
        userFollow.setUserId(userFollowing);

        followUsersId.setFollower(userOptional.get());
        followUsersId.setUser(userFollow);

        followUser.setId(followUsersId);

        eventPublisher.publishEvent(new FollowUserEvent(this, followUsersId.getUser().getUserId(), followUsersId.getFollower().getUserId()));

        return followUserRepository.save(followUser);
    }

    @Override
    public Integer delete(Integer userFollowing) {
        Optional<User> userOptional = authService.getLoggedUser();

        if(userOptional.isEmpty()) {
            throw new EntityNotFoundException("Authentication error");
        }

        FollowUsersId followUsersId = new FollowUsersId();
        User userObj = new User();

        userObj.setUserId(userFollowing);

        followUsersId.setUser(userObj);
        followUsersId.setFollower(userOptional.get());

        followUserRepository.deleteById(followUsersId);

        return 1;
    }

    @Override
    public Page<FollowUsersResponse> findFollowersByUser(Integer userId, Pageable pageable) {
        return followUserRepository.findFollowersByUser(userId, pageable);
    }
}
