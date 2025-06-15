package com.jv.forum_api.mapper;

import com.jv.forum_api.dto.posts.PostSave;
import com.jv.forumapi.entities.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post mapToEntity(PostSave postSave) {
        Post post = new Post();
        post.setContent(postSave.content());
        post.setTitle(postSave.title());
        return post;
    }

}
