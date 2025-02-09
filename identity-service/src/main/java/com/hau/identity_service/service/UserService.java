package com.hau.identity_service.service;

import com.hau.identity_service.dto.request.UserCreationRequest;
import com.hau.identity_service.dto.request.UserUpdateRequest;
import com.hau.identity_service.dto.response.ApiResponse;
import com.hau.identity_service.dto.response.UserResponse;
import com.hau.identity_service.entity.User;
import com.hau.identity_service.exception.AppException;
import com.hau.identity_service.exception.ErrorCode;
import com.hau.identity_service.exception.SuccessMessage;
import com.hau.identity_service.mapper.UserMapper;
import com.hau.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public ApiResponse<UserResponse> createUser(UserCreationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        User user = userMapper.toUser(request);
        User savedUser = userRepository.save(user); // Lưu user để lấy ID
        UserResponse userResponse = userMapper.toUserResponse(savedUser);
        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message(SuccessMessage.CREATED_USER.getMessage())
                .result(userResponse)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public ApiResponse<UserResponse> updateUser(Integer userId, UserUpdateRequest request) {
        User user = findUserById(userId);
        userMapper.updateUser(user, request);
        User updatedUser = userRepository.save(user);
        UserResponse userResponse = userMapper.toUserResponse(updatedUser);
        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.UPDATE_USER.getMessage())
                .result(userResponse)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public ApiResponse<List<UserResponse>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());

        return ApiResponse.<List<UserResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.GET_ALL_USERS.getMessage())
                .result(userResponses)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public ApiResponse<UserResponse> getUserById(int id) {
        User user = findUserById(id);
        UserResponse userResponse = userMapper.toUserResponse(user);
        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.GET_USER_BY_ID.getMessage())
                .result(userResponse)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public ApiResponse<Void> deleteUserById(int id) {
        if(!userRepository.existsById(id))
            throw  new AppException(ErrorCode.USER_NOT_FOUND);
        userRepository.deleteById(id);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message(SuccessMessage.DELETE_USER.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    private User findUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }
}