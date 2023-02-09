package com.kakashi.user.service.UserService.service;

import com.kakashi.user.service.UserService.dto.UserRequest;
import com.kakashi.user.service.UserService.dto.UserResponse;
import com.kakashi.user.service.UserService.model.User;

import java.util.List;

public interface UserService {
    // user operations

    // create
    User saveUser(UserRequest userRequest) ;

    // get all user
    List<UserResponse> getAllUser() ;

    // get single user of user id

    User getUser(String userId) ;
}
