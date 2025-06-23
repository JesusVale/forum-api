package com.jv.forum_api.mapper;

import com.jv.forum_api.dto.comments.PostCommentSave;
import com.jv.forumapi.entities.Post;
import com.jv.forumapi.entities.PostComment;
import org.springframework.stereotype.Component;

@Component
public class PostCommentMapper {

    public PostComment mapToEntity(PostCommentSave postCommentSave) {
        PostComment postComment = new PostComment();
        Post post = new Post();
        post.setPostId(postCommentSave.rootPost());

        if(postCommentSave.commentPost() != null) {
            PostComment commentPost = new PostComment();
            commentPost.setCommentId(postCommentSave.commentPost());
            postComment.setCommentPost(commentPost);
        }

        postComment.setRootPost(post);
        postComment.setContent(postCommentSave.content());
        return postComment;
    }

}
