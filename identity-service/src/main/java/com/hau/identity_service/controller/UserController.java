package com.hau.identity_service.controller;

import com.hau.identity_service.dto.request.UserCreationRequest;
import com.hau.identity_service.dto.request.UserUpdateRequest;
import com.hau.identity_service.dto.response.ApiResponse;
import com.hau.identity_service.dto.response.UserResponse;
import com.hau.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody UserCreationRequest request) {
        ApiResponse<UserResponse> response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable Integer userId,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        ApiResponse<UserResponse> response = userService.updateUser(userId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        ApiResponse<List<UserResponse>> response = userService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable int userId) {
        ApiResponse<UserResponse> response = userService.getUserById(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUserById(@PathVariable int userId) {
        ApiResponse<Void> response = userService.deleteUserById(userId);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}