package com.kakashi.user.service.UserService.service;

import com.kakashi.user.service.UserService.dto.UserRequest;
import com.kakashi.user.service.UserService.dto.UserResponse;
import com.kakashi.user.service.UserService.exception.ResourceNotFoundException;
import com.kakashi.user.service.UserService.model.User;
import com.kakashi.user.service.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(UserRequest userRequest) {
        // mapping User model with UserRequest DTO
        String randomUserId = UUID.randomUUID().toString();
        userRequest.setUserId(randomUserId);
        User user = User.builder()
                .userId(userRequest.getUserId())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .about(userRequest.getAbout())
                .build();
        return userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapToUserResponse(user)).toList();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given" +
                "id is not found on server !! " + userId));
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .about(user.getEmail())
                .build();
    }
}

