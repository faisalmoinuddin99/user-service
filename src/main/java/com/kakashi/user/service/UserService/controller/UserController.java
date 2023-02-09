package com.kakashi.user.service.UserService.controller;

import com.kakashi.user.service.UserService.dto.UserRequest;
import com.kakashi.user.service.UserService.dto.UserResponse;
import com.kakashi.user.service.UserService.model.User;
import com.kakashi.user.service.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService ;

    // create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest){

        User user = userService.saveUser(userRequest) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(user) ;
    }

    // single user get
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
      User user =  userService.getUser(userId) ;
      return ResponseEntity.ok(user) ;
    }
    // all user get
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser(){
       List<UserResponse> users = userService.getAllUser() ;
       return ResponseEntity.ok(users) ;
    }
}
