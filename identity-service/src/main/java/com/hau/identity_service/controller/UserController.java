package com.hau.identity_service.controller;

import com.hau.identity_service.dto.request.UserCreationRequest;
import com.hau.identity_service.dto.request.UserUpdateRequest;
import com.hau.identity_service.dto.response.ApiResponse;
import com.hau.identity_service.entity.User;
import com.hau.identity_service.exception.SuccessMessage;
import com.hau.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<User> addUser(@RequestBody @Valid UserCreationRequest request) {
        User createdUser = userService.createUser(request);
        return new ApiResponse<>(HttpStatus.CREATED.value(),SuccessMessage.CREATED_USER.getMessage(), createdUser);
    }

    @GetMapping
    public ApiResponse<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return  new ApiResponse<>(HttpStatus.OK.value(),SuccessMessage.GET_ALL_USERS.getMessage(), users);
    }

    @GetMapping("/{userId}")
    public ApiResponse<User> getUserById(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return new ApiResponse<>(HttpStatus.OK.value(),SuccessMessage.GET_USER_BY_ID.getMessage(), user);
    }

    @PutMapping("/{userId}")
    public ApiResponse<User> updateUser(@PathVariable Integer userId, @RequestBody @Valid UserUpdateRequest request) {
        User updatedUser = userService.updateUser(userId, request);
        return new ApiResponse<>(HttpStatus.OK.value(),SuccessMessage.UPDATE_USER.getMessage(), updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NO_CONTENT.value(), SuccessMessage.DELETE_USER.getMessage(), null), HttpStatus.NO_CONTENT);
    }
}