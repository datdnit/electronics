package com.hau.identity_service.service;

import com.hau.identity_service.dto.request.UserCreationRequest;
import com.hau.identity_service.dto.request.UserUpdateRequest;
import com.hau.identity_service.entity.User;
import com.hau.identity_service.exception.AppException;
import com.hau.identity_service.exception.ErrorCode;
import com.hau.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User user = new User();

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setRoleId(request.getRoleId());
        user.setCreatedAt(request.getCreatedAt());
        user.setUpdatedAt(request.getUpdatedAt());

        return userRepository.save(user);
    }

    public User updateUser(Integer userId, UserUpdateRequest request) {
        User user = getUserById(userId);

        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setRoleId(request.getRoleId());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
