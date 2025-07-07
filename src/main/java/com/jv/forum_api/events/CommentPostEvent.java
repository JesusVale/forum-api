package com.jv.forum_api.events;

import com.jv.forumapi.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
public class CommentPostEvent extends ApplicationEvent {

    private Integer postCommented;
    private User userComment;

    public CommentPostEvent(Object source, Integer postCommented, User userComment) {
        super(source);
        this.postCommented = postCommented;
        this.userComment = userComment;
    }

}
