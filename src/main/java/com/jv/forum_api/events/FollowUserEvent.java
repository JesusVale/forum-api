package com.jv.forum_api.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class FollowUserEvent extends ApplicationEvent {

    private Integer userId;
    private Integer followerId;

    public FollowUserEvent(Object source, Integer userId, Integer followerId) {
        super(source);
        this.userId = userId;
        this.followerId = followerId;
    }

    public String getContent(String actorUsername) {
        return actorUsername + " followed you";
    }
}
