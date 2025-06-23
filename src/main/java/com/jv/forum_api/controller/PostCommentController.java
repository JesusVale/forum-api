package com.jv.forum_api.controller;

import com.jv.forum_api.dto.comments.PostCommentResponse;
import com.jv.forum_api.dto.comments.PostCommentSave;
import com.jv.forum_api.service.interfaces.IPostCommentService;
import com.jv.forumapi.entities.PostComment;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/comments")
public class PostCommentController {

    private final IPostCommentService postCommentService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PostCommentSave postCommentSave) {
        PostComment postComment = postCommentService.save(postCommentSave);

        return new ResponseEntity<>(postComment, HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> findByRootPost(@PathVariable Integer postId, Pageable pageable) {

        Page<PostCommentResponse> response = postCommentService.findByRootPost(postId, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<?> findByCommentPost(@PathVariable Integer commentId, Pageable pageable){
        Page<PostCommentResponse> response = postCommentService.findByCommentPost(commentId, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
