package com.jv.forum_api.listener;

import com.jv.forum_api.events.FollowUserEvent;
import com.jv.forum_api.repository.NotificationRepository;
import com.jv.forum_api.repository.UserRepository;
import com.jv.forumapi.entities.Notification;
import com.jv.forumapi.entities.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FollowUserListener {

    private NotificationRepository notificationRepository;
    private UserRepository userRepository;

    @EventListener
    public void handleFollowUserEvent(FollowUserEvent event) {

        User userFollower = userRepository.findById(event.getFollowerId()).orElseThrow(()-> new EntityNotFoundException("User not found"));
        User user = new User();
        user.setUserId(event.getUserId());

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setActor(userFollower);
        notification.setContent(event.getContent(userFollower.getUsername()));

        notificationRepository.save(notification);
    }


}
