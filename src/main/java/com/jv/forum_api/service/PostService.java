package com.jv.forum_api.service;

import com.jv.forum_api.dto.posts.PostFilter;
import com.jv.forum_api.dto.posts.PostResponse;
import com.jv.forum_api.dto.posts.PostSave;
import com.jv.forum_api.mapper.PostMapper;
import com.jv.forum_api.repository.PostRepository;
import com.jv.forum_api.repository.custom.PostQueryService;
import com.jv.forum_api.service.interfaces.IAuthService;
import com.jv.forum_api.service.interfaces.IPostService;
import com.jv.forumapi.entities.Post;
import com.jv.forumapi.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService implements IPostService {

    private PostRepository postRepository;

    private PostQueryService postQueryService;

    private IAuthService authService;

    private PostMapper postMapper;

    @Override
    public PostResponse save(PostSave post) {

        Post postSave = postMapper.mapToEntity(post);

        Post savedPost = postRepository.saveAndFlush(postSave);

        return postMapper.mapToResponse(savedPost);
    }

    @Override
    public PostResponse findByPostId(Integer postId) {
        return postRepository.findByPostId(postId);
    }

    @Override
    public Page<PostResponse> findByUsersFollowed(Pageable pageable) {

        Optional<User> userOptional = authService.getLoggedUser();

        if (userOptional.isEmpty()) {
            throw  new RuntimeException("User not found");
        }

        User user = userOptional.get();
        return postRepository.findByUsersFollowed(user.getUserId(), pageable);
    }

    @Override
    public Page<PostResponse> findByFilters(PostFilter filter, Pageable pageable) {
        return postQueryService.findByFilters(filter, pageable);
    }
}
