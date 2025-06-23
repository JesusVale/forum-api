package com.jv.forum_api.service;

import com.jv.forum_api.dto.comments.PostCommentResponse;
import com.jv.forum_api.dto.comments.PostCommentSave;
import com.jv.forum_api.mapper.PostCommentMapper;
import com.jv.forum_api.repository.PostCommentRepository;
import com.jv.forum_api.repository.PostRepository;
import com.jv.forum_api.service.interfaces.IPostCommentService;
import com.jv.forumapi.entities.Post;
import com.jv.forumapi.entities.PostComment;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PostCommentService implements IPostCommentService {

    private PostCommentRepository postCommentRepository;

    private PostRepository postRepository;

    private PostCommentMapper postCommentMapper;

    @Override
    public PostComment save(PostCommentSave postCommentSave) {

        if(!postRepository.existsByPostId(postCommentSave.rootPost())){
            throw new EntityNotFoundException("Post not found");
        }

        PostComment postComment = postCommentMapper.mapToEntity(postCommentSave);

        if(postCommentSave.commentPost() != null) {

            PostCommentResponse postCommentResponse = postCommentRepository.findByCommentId(postCommentSave.commentPost());

            if(postCommentResponse == null) {
                throw new EntityNotFoundException("Comment not found");
            }

            Post rootPost = new Post();
            rootPost.setPostId(postCommentResponse.rootPost().postId());
            postComment.setRootPost(rootPost);
        }

        return postCommentRepository.save(postComment);
    }

    @Override
    public Page<PostCommentResponse> findByRootPost(Integer post, Pageable pageable) {
        return postCommentRepository.findByRootPost(post, pageable);
    }

    @Override
    public Page<PostCommentResponse> findByCommentPost(Integer comment, Pageable pageable) {
        return postCommentRepository.findByCommentPost(comment, pageable);
    }
}
