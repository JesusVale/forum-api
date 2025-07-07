package com.jv.forum_api.service;

import com.jv.forum_api.dto.posts.PostFilter;
import com.jv.forum_api.dto.posts.PostResponse;
import com.jv.forum_api.dto.posts.PostSave;
import com.jv.forum_api.mapper.PostMapper;
import com.jv.forum_api.repository.PostRepository;
import com.jv.forum_api.repository.PostTagRepository;
import com.jv.forum_api.repository.custom.PostQueryService;
import com.jv.forum_api.service.interfaces.IAuthService;
import com.jv.forum_api.service.interfaces.IPostService;
import com.jv.forumapi.entities.Post;
import java.util.List;
import com.jv.forumapi.entities.User;
import com.jv.forumapi.enums.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class PostService implements IPostService {

    private PostRepository postRepository;

    private PostTagRepository postTagRepository;

    private PostQueryService postQueryService;

    private IAuthService authService;

    private PostMapper postMapper;

    @Override
    public PostResponse save(PostSave post) {

        Post postSave = postMapper.mapToEntity(post);

        Post savedPost = postRepository.saveAndFlush(postSave);

        return postMapper.mapToResponse(savedPost, post.tags());
    }

    @Override
    public PostResponse findByPostId(Integer postId) {
        return  getPostResponseWithTags(postRepository.findByPostId(postId));
    }

    @Override
    public Page<PostResponse> findByUsersFollowed(Pageable pageable) {

        Optional<User> userOptional = authService.getLoggedUser();

        if (userOptional.isEmpty()) {
            throw  new RuntimeException("Authentication error");
        }

        User user = userOptional.get();

        Page<PostResponse> postResponsePage = postRepository.findByUsersFollowed(user.getUserId(), pageable);

        return postResponsePage.map(this::getPostResponseWithTags);
    }

    @Override
    public Page<PostResponse> findByFilters(PostFilter filter, Pageable pageable) {

        Page<PostResponse> postResponsePage = postQueryService.findByFilters(filter, pageable);

        return postResponsePage.map(this::getPostResponseWithTags);
    }


    private PostResponse getPostResponseWithTags(PostResponse postResponse) {

        Set<Tag> postTags = postRepository.findTagsByPostId(postResponse.postId());

        return new PostResponse(
                postResponse.postId(),
                postResponse.title(),
                postResponse.content(),
                postResponse.createdBy(),
                postResponse.createdAt(),
                postTags
        );

    }
}
