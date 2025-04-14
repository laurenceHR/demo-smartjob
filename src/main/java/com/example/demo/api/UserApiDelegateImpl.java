package com.example.demo.api;

import com.example.demo.model.UserResponse;
import com.example.demo.model.UsersPostRequest;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserApiDelegateImpl implements UsersApiDelegate {

    private UserService userService;

    public UserApiDelegateImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserResponse> usersPost(UsersPostRequest usersPostRequest) {
        userService.saveUser(usersPostRequest);
        UserResponse userResponse = userService.getUserByEmail(usersPostRequest.getEmail());

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
