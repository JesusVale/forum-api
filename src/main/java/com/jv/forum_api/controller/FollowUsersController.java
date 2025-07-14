package com.jv.forum_api.controller;

import com.jv.forum_api.dto.follows.FollowUsersResponse;
import com.jv.forum_api.service.interfaces.IFollowUserService;
import com.jv.forumapi.entities.FollowUsers;
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
        name = "Follows Controller",
        description = "Operations related to the following of a user"
)
@AllArgsConstructor
@RestController
@RequestMapping("/follows")
public class FollowUsersController {

    private IFollowUserService followUserService;

    @Operation(
            summary = "Allows a user to follow another user by their ID",
            description = "Returns the user saves and encrypts the password"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "User followed successfully"),
            @ApiResponse(responseCode = "404", description = "The user to be followed doesn't exist")
    })
    @PostMapping("/followee/{userId}")
    public ResponseEntity<?> save(
            @Parameter(description = "Id of the user to follow")
            @PathVariable Integer userId){

        FollowUsers followUsers = followUserService.save(userId);

        return new ResponseEntity<>(followUsers, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Allows a user to unfollow another user by their ID",
            description = "Returns the user saves and encrypts the password"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "User unfollowed successfully"),
            @ApiResponse(responseCode = "404", description = "The user to be unfollowed doesn't exist")
    })
    @DeleteMapping("/followee/{userId}")
    public ResponseEntity<?> delete(
            @Parameter(description = "Id of the user to unfollow")
            @PathVariable Integer userId){
        Integer deleted = followUserService.delete(userId);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve a user's followers",
            description = "Fetches and returns a list of all users who follow the specified user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Followers list retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> findFollowersByUser(
            @Parameter(description = "ID of the user whose followers are to be retrieved")
            @PathVariable Integer userId, Pageable pageable){
        Page<FollowUsersResponse> followUsers = followUserService.findFollowersByUser(userId, pageable);

        return new ResponseEntity<>(followUsers, HttpStatus.OK);
    }

}
