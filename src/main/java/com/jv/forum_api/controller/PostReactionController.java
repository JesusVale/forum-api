package com.jv.forum_api.controller;

import com.jv.forum_api.dto.posts.PostReactionSave;
import com.jv.forum_api.service.interfaces.IPostReactionService;
import com.jv.forumapi.entities.PostReaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Post Reaction controller",
        description = "Operations related to the post reaction"
)
@RestController
@AllArgsConstructor
@RequestMapping("/post-reaction")
public class PostReactionController {

    private final IPostReactionService postReactionService;

    @Operation(
            summary = "Adds a reaction to a post",
            description = "Returns the post reaction saved"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "User followed successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody PostReactionSave postReactionSave) {

        PostReaction postReactionSaved = postReactionService.save(postReactionSave);

        return new ResponseEntity<>(postReactionSaved, HttpStatus.CREATED);

    }

    @Operation(
            summary = "Removes a reaction to a post",
            description = "Returns the post reaction deleted"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "User followed successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @DeleteMapping("/{post}")
    public ResponseEntity<?> delete(@PathVariable Integer post) {

        Integer value = postReactionService.delete(post);

        return new ResponseEntity<>(value, HttpStatus.OK);
    }

}
