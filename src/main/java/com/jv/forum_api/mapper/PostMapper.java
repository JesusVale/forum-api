package com.jv.forum_api.mapper;

import com.jv.forum_api.dto.posts.PostResponse;
import com.jv.forum_api.dto.posts.PostSave;
import com.jv.forum_api.dto.users.UserSimpleResponse;
import com.jv.forumapi.entities.Post;
import com.jv.forumapi.enums.Tag;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PostMapper {

    public Post mapToEntity(PostSave postSave) {
        Post post = new Post();
        post.setContent(postSave.content());
        post.setTitle(postSave.title());
        post.setTags(postSave.tags());
        return post;
    }

    public PostResponse mapToResponse(Post post, Set<Tag> tags) {

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
                post.getCreatedAt(),
                tags
        );


    }

}
