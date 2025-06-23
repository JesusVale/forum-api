package com.jv.forum_api.service;

import com.jv.forum_api.dto.posts.PostReactionSave;
import com.jv.forum_api.mapper.PostReactionMapper;
import com.jv.forum_api.repository.PostReactionRepository;
import com.jv.forum_api.repository.PostRepository;
import com.jv.forum_api.service.interfaces.IPostReactionService;
import com.jv.forumapi.entities.Post;
import com.jv.forumapi.entities.PostReaction;
import com.jv.forumapi.entities.PostReactionId;
import com.jv.forumapi.entities.User;
import com.jv.forumapi.enums.Reaction;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PostReactionService implements IPostReactionService {

    private PostReactionRepository postReactionRepository;

    private PostRepository postRepository;

    private PostReactionMapper postReactionMapper;

    private AuthService authService;

    @Override
    public PostReaction save(PostReactionSave postReactionSave) {

        Optional<User> userOptional = authService.getLoggedUser();

        if(userOptional.isEmpty()) {
            throw new RuntimeException("Authentication error");
        }

        PostReaction postReaction = postReactionMapper.mapToEntity(postReactionSave, userOptional.get());

        postReactionRepository.deleteById(postReaction.getPostReactionId());

        return postReactionRepository.saveAndFlush(postReaction);
    }

    @Override
    public Integer delete(Integer post) {

        Optional<User> userOptional = authService.getLoggedUser();

        if(userOptional.isEmpty()) {
            throw new RuntimeException("Authentication error");
        }

        postRepository.findById(post).orElseThrow(() -> new EntityNotFoundException("Post not found"));

        PostReactionId postReactionId = new PostReactionId();
        Post postObj = new Post();
        postObj.setPostId(post);

        postReactionId.setPost(postObj);
        postReactionId.setUser(userOptional.get());

        postReactionRepository.deleteById(postReactionId);

        return 1;
    }
}
