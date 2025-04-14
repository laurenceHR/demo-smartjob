package com.example.demo.service;

import com.example.demo.model.UserResponse;
import com.example.demo.model.UsersPostRequest;

public interface UserService {
    void saveUser(UsersPostRequest request);

    UserResponse getUserByEmail(String email);
}
