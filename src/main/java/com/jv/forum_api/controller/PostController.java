package com.jv.forum_api.controller;

import com.jv.forum_api.dto.posts.PostFilter;
import com.jv.forum_api.dto.posts.PostResponse;
import com.jv.forum_api.dto.posts.PostSave;
import com.jv.forum_api.service.interfaces.IPostService;
import com.jv.forumapi.entities.Post;
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

import java.util.List;

@Tag(
        name = "Posts Controller",
        description = "Operations related the posts"
)
@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final IPostService postService;

    @Operation(
            summary = "Creates a comment",
            description = "Creates a comment of a post"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Post created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody PostSave post){

        PostResponse savedPost = postService.save(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);

    }

    @Operation(
            summary = "Retrieve a post by ID",
            description = "Fetches and returns the post corresponding to the provided ID"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Post retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @Parameter(description = "ID of the post to retrieve")
            @PathVariable Integer id){

        PostResponse postFound = postService.findByPostId(id);
        return new ResponseEntity<>(postFound, HttpStatus.OK);

    }

    @Operation(
            summary = "Retrieves a list of posts",
            description = "Returns a paginated list of posts created by users that the currently logged-in user follows"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Posts retrieved successfully"),
    })
    @GetMapping("/search/feed")
    public ResponseEntity<?> getFeed(Pageable pageable){

        Page<PostResponse> postsFeed = postService.findByUsersFollowed(pageable);
        return new ResponseEntity<>(postsFeed, HttpStatus.OK);

    }

    @Operation(
            summary = "Retrieves a list of posts",
            description = "Returns a paginated list of posts with the selected filters"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Posts retrieved successfully"),
    })
    @GetMapping
    public ResponseEntity<?> getPosts(PostFilter filter, Pageable pageable){

        Page<PostResponse> postResponses = postService.findByFilters(filter, pageable);

        return new ResponseEntity<>(postResponses, HttpStatus.OK);

    }

}
