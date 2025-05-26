package com.jv.forum_api.controller;

import com.jv.forum_api.dto.users.UserCreate;
import com.jv.forum_api.dto.users.UserSimpleResponse;
import com.jv.forum_api.dto.users.UserUpdate;
import com.jv.forum_api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Validated
public class AuthController {

    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserSimpleResponse> save(@Valid @RequestBody UserCreate user) {

        UserSimpleResponse userSaved = service.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserCreate user) {

        String token = service.login(user);

        return ResponseEntity.status(HttpStatus.OK).body(token);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserSimpleResponse> update(@PathVariable Integer id, @Valid @RequestBody UserUpdate user) {
        UserSimpleResponse userUpdated = service.update(id, user);

        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }



}
