package com.jv.forum_api.service.interfaces;

import com.jv.forum_api.dto.comments.PostCommentResponse;
import com.jv.forum_api.dto.comments.PostCommentSave;
import com.jv.forumapi.entities.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostCommentService {

    public PostComment save(PostCommentSave postCommentSave);

    public Page<PostCommentResponse> findByRootPost(Integer post, Pageable pageable);

    public Page<PostCommentResponse> findByCommentPost(Integer comment, Pageable pageable);

}
