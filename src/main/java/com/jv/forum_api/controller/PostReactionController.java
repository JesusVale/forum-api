package com.jv.forum_api.controller;

import com.jv.forum_api.dto.posts.PostReactionSave;
import com.jv.forum_api.service.interfaces.IPostReactionService;
import com.jv.forumapi.entities.PostReaction;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/post-reaction")
public class PostReactionController {

    private final IPostReactionService postReactionService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PostReactionSave postReactionSave) {

        PostReaction postReactionSaved = postReactionService.save(postReactionSave);

        return new ResponseEntity<>(postReactionSaved, HttpStatus.CREATED);

    }

    @DeleteMapping("/{post}")
    public ResponseEntity<?> delete(@PathVariable Integer post) {

        Integer value = postReactionService.delete(post);

        return new ResponseEntity<>(value, HttpStatus.OK);
    }

}
