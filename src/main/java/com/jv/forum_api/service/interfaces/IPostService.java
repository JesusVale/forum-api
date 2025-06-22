package com.jv.forum_api.service.interfaces;

import com.jv.forum_api.dto.posts.PostFilter;
import com.jv.forum_api.dto.posts.PostResponse;
import com.jv.forum_api.dto.posts.PostSave;
import com.jv.forumapi.entities.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService {

    public PostResponse save(PostSave post);

    public PostResponse findByPostId(Integer postId);

    public List<PostResponse> findByUsersFollowed(Pageable pageable);

    public List<PostResponse> findByFilters(PostFilter filter, Pageable pageable);

}
