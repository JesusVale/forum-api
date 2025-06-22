package com.jv.forum_api.mapper;

import com.jv.forum_api.dto.posts.PostReactionSave;
import com.jv.forumapi.entities.Post;
import com.jv.forumapi.entities.PostReaction;
import com.jv.forumapi.entities.PostReactionId;
import com.jv.forumapi.entities.User;
import org.springframework.stereotype.Component;

@Component
public class PostReactionMapper {

    public PostReaction mapToEntity(PostReactionSave postReactionSave, User user){

        PostReaction postReaction = new PostReaction();

        PostReactionId postReactionId = new PostReactionId();

        Post post = new Post();
        post.setPostId(postReactionSave.post());

        postReactionId.setPost(post);
        postReactionId.setUser(user);

        postReaction.setPostReactionId(postReactionId);
        postReaction.setReaction(postReactionSave.reaction());

        return postReaction;

    }

}
