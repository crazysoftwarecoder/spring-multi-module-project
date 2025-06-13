package com.example.application.controller;

import com.example.application.service.UserService;
import com.example.common.dto.CreateUserRequest;
import com.example.common.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        User createdUser = userService.createUser(request);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{handle}")
    public ResponseEntity<User> getUserByHandle(@PathVariable String handle) {
        User user = userService.getUserByHandle(handle);
        return ResponseEntity.ok(user);
    }
} 