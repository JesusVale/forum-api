package com.jv.forum_api.controller;

import com.jv.forum_api.dto.follows.FollowUsersResponse;
import com.jv.forum_api.service.interfaces.IFollowUserService;
import com.jv.forumapi.entities.FollowUsers;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/follows")
public class FollowUsersController {

    private IFollowUserService followUserService;

    @PostMapping("/followee/{userId}")
    public ResponseEntity<?> save(@PathVariable Integer userId){

        FollowUsers followUsers = followUserService.save(userId);

        return new ResponseEntity<>(followUsers, HttpStatus.CREATED);
    }

    @DeleteMapping("/followee/{userId}")
    public ResponseEntity<?> delete(@PathVariable Integer userId){
        Integer deleted = followUserService.delete(userId);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> findFollowersByUser(@PathVariable Integer userId, Pageable pageable){
        Page<FollowUsersResponse> followUsers = followUserService.findFollowersByUser(userId, pageable);

        return new ResponseEntity<>(followUsers, HttpStatus.OK);
    }

}
