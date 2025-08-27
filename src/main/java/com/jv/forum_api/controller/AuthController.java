package com.jv.forum_api.controller;

import com.jv.forum_api.dto.users.UserCreate;
import com.jv.forum_api.dto.users.UserSimpleResponse;
import com.jv.forum_api.dto.users.UserUpdate;
import com.jv.forum_api.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "Authentication Controller",
        description = "Operations related to the authentication of a User"
)
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Validated
public class AuthController {

    private IUserService service;

    @Operation(
            summary = "Create new User",
            description = "Returns the user saves and encrypts the password"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/register")
    public ResponseEntity<UserSimpleResponse> save(@Valid @RequestBody UserCreate user) {

        UserSimpleResponse userSaved = service.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);

    }

    @Operation(
            summary = "Login User",
            description = "Returns a JWT token"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "500", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserCreate user) {

        String token = service.login(user);

        return ResponseEntity.status(HttpStatus.OK).body(token);

    }

    @Operation(
            summary = "Update User",
            description = "Returns a updated user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User with id doesn't exists"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserSimpleResponse> update(@PathVariable Integer id, @Valid @RequestBody UserUpdate user) {
        UserSimpleResponse userUpdated = service.update(id, user);

        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }



}
