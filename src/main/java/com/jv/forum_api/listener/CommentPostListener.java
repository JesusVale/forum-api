package com.jv.forum_api.listener;

import com.jv.forum_api.dto.posts.PostResponse;
import com.jv.forum_api.events.CommentPostEvent;
import com.jv.forum_api.repository.NotificationRepository;
import com.jv.forum_api.repository.PostRepository;
import com.jv.forumapi.entities.Notification;
import com.jv.forumapi.entities.User;
import com.jv.forumapi.enums.NotificationType;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CommentPostListener {

    private NotificationRepository notificationRepository;
    private PostRepository postRepository;

    @EventListener
    public void handleCommentPostEvent(CommentPostEvent event) {
        
        PostResponse post = postRepository.findByPostId(event.getPostCommented());

        User user = new User();
        user.setUserId(post.createdBy().userId());

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setActor(event.getUserComment());
        notification.setContent(event.getUserComment().getUsername() + " commented on your post");
        notification.setType(NotificationType.RESPONSE);

        notificationRepository.save(notification);
    }

}
