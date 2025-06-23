package com.jv.forum_api.service.interfaces;

import com.jv.forum_api.dto.posts.PostFilter;
import com.jv.forum_api.dto.posts.PostResponse;
import com.jv.forum_api.dto.posts.PostSave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostService {

    public PostResponse save(PostSave post);

    public PostResponse findByPostId(Integer postId);

    public Page<PostResponse> findByUsersFollowed(Pageable pageable);

    public Page<PostResponse> findByFilters(PostFilter filter, Pageable pageable);

}
