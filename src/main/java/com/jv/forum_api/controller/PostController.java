package com.jv.forum_api.controller;

import com.jv.forum_api.dto.posts.PostFilter;
import com.jv.forum_api.dto.posts.PostResponse;
import com.jv.forum_api.dto.posts.PostSave;
import com.jv.forum_api.service.interfaces.IPostService;
import com.jv.forumapi.entities.Post;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final IPostService postService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PostSave post){

        PostResponse savedPost = postService.save(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){

        PostResponse postFound = postService.findByPostId(id);
        return new ResponseEntity<>(postFound, HttpStatus.OK);

    }

    @GetMapping("/search/feed")
    public ResponseEntity<?> getFeed(Pageable pageable){

        Page<PostResponse> postsFeed = postService.findByUsersFollowed(pageable);
        return new ResponseEntity<>(postsFeed, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<?> getPosts(PostFilter filter, Pageable pageable){

        Page<PostResponse> postResponses = postService.findByFilters(filter, pageable);

        return new ResponseEntity<>(postResponses, HttpStatus.OK);

    }

}
