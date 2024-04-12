package com.ivan.passwordmanager.controllers;

import com.ivan.passwordmanager.dto.UserDto;
import com.ivan.passwordmanager.model.User;
import com.ivan.passwordmanager.service.UserService;
import com.ivan.passwordmanager.service.impl.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(this.userService.getAllUsers(pageable));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> removeUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(this.userService.removeUserById(userId));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable long userId, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(this.userService.updateUserById(userId, userDto), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<User> patchUser(@PathVariable long userId, @RequestBody Map<String, Object> fields) {
        return new ResponseEntity<>(this.userService.patchUserById(userId, fields), HttpStatus.ACCEPTED);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.createUser(user));
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

}
