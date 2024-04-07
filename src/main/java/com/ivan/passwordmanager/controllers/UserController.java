package com.ivan.passwordmanager.controllers;

import com.ivan.passwordmanager.dto.UserDto;
import com.ivan.passwordmanager.service.impl.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(this.userService.getAllUsers(pageable));
    }

    @DeleteMapping("users/{userId}")
    public ResponseEntity<?> removeUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(this.userService.removeUserById(userId));
    }



}
