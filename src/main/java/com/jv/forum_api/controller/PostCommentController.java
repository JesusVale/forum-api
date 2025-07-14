package com.jv.forum_api.controller;

import com.jv.forum_api.dto.comments.PostCommentResponse;
import com.jv.forum_api.dto.comments.PostCommentSave;
import com.jv.forum_api.service.interfaces.IPostCommentService;
import com.jv.forumapi.entities.PostComment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Comments Controller",
        description = "Operations related to the comments of a post"
)
@AllArgsConstructor
@RestController
@RequestMapping("/comments")
public class PostCommentController {

    private final IPostCommentService postCommentService;

    @Operation(
            summary = "Creates a comment",
            description = "Creates a comment of a post"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Comment created successfully"),
            @ApiResponse(responseCode = "404", description = "The post doesn't exist"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody PostCommentSave postCommentSave) {
        PostComment postComment = postCommentService.save(postCommentSave);

        return new ResponseEntity<>(postComment, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Retrieve comments of a root post",
            description = "Returns a paginated list of all comments associated with the specified post"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @GetMapping("/post/{postId}")
    public ResponseEntity<?> findByRootPost(@Parameter(description = "ID of the post") @PathVariable Integer postId, Pageable pageable) {

        Page<PostCommentResponse> response = postCommentService.findByRootPost(postId, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve comments of a comment",
            description = "Returns a paginated list of all comments associated with the specified comment"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<?> findByCommentPost(@PathVariable Integer commentId, Pageable pageable){
        Page<PostCommentResponse> response = postCommentService.findByCommentPost(commentId, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
