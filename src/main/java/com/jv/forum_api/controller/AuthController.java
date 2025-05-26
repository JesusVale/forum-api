package com.jv.forum_api.controller;

import com.jv.forum_api.dto.UserCreate;
import com.jv.forum_api.dto.UserSimpleResponse;
import com.jv.forum_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthController {

    private UserService service;

    @PostMapping
    public ResponseEntity<UserSimpleResponse> save(@RequestBody UserCreate user) {

        UserSimpleResponse userSaved = service.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCreate user) {

        String token = service.login(user);

        return ResponseEntity.status(HttpStatus.OK).body(token);

    }

}
