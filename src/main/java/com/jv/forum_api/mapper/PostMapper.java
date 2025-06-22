package com.jv.forum_api.mapper;

import com.jv.forum_api.dto.posts.PostResponse;
import com.jv.forum_api.dto.posts.PostSave;
import com.jv.forum_api.dto.users.UserSimpleResponse;
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

    public PostResponse mapToResponse(Post post) {

        UserSimpleResponse user = new UserSimpleResponse(
                post.getCreatedBy().getUserId(),
                post.getCreatedBy().getUsername(),
                post.getCreatedBy().getEmail()
        );

        return new PostResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                user,
                post.getCreatedAt()
        );


    }

}
