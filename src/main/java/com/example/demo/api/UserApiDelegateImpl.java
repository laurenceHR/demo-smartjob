package com.example.demo.api;

import com.example.demo.model.UserResponse;
import com.example.demo.model.UsersPostRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserApiDelegateImpl implements UsersApiDelegate {

    @Override
    public ResponseEntity<UserResponse> usersPost(UsersPostRequest usersPostRequest) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
