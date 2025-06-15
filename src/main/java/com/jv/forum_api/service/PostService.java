package com.jv.forum_api.service;

import com.jv.forum_api.dto.posts.PostFilter;
import com.jv.forum_api.dto.posts.PostResponse;
import com.jv.forum_api.dto.posts.PostSave;
import com.jv.forum_api.mapper.PostMapper;
import com.jv.forum_api.repository.PostRepository;
import com.jv.forum_api.repository.UserRepository;
import com.jv.forum_api.repository.custom.PostQueryService;
import com.jv.forum_api.service.interfaces.IPostService;
import com.jv.forumapi.entities.Post;
import com.jv.forumapi.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService implements IPostService {

    private PostRepository postRepository;

    private PostQueryService postQueryService;

    private UserRepository userRepository;

    private PostMapper postMapper;

    @Override
    public Post save(PostSave post) {

        Post postSave = postMapper.mapToEntity(post);

        return postRepository.saveAndFlush(postSave);
    }

    @Override
    public PostResponse findByPostId(Integer postId) {
        return postRepository.findByPostId(postId);
    }

    @Override
    public List<PostResponse> findByUsersFollowed(Pageable pageable) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername());

        return postRepository.findByUsersFollowed(user.getUserId(), pageable).toList();
    }

    @Override
    public List<PostResponse> findByFilters(PostFilter filter, Pageable pageable) {
        return postQueryService.findByFilters(filter, pageable).toList();
    }
}
