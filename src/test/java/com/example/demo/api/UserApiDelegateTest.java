package com.example.demo.api;

import com.example.demo.model.UserResponse;
import com.example.demo.model.UsersPostRequest;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserApiDelegateTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserApiDelegateImpl userApiDelegate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test() {
        // Arrange
        UsersPostRequest request = new UsersPostRequest();
        request.setName("Juan Rodriguez");
        request.setEmail("juan@rodriguez.org");
        request.setPassword("hunter2");

        UserResponse expectedResponse = new UserResponse();
        //expectedResponse.setId("123e4567-e89b-12d3-a456-426614174000");
        expectedResponse.setCreated("2025-04-09T22:34:29Z");
        expectedResponse.setModified("2025-04-09T22:34:29Z");
        expectedResponse.setLastLogin("2025-04-09T22:34:29Z");
        expectedResponse.setToken("123e4567-e89b-12d3-a456-426614174000");
        expectedResponse.setIsactive(true);

        when(userService.getUserByEmail(request.getEmail())).thenReturn(expectedResponse);

        // Act
        ResponseEntity<UserResponse> response = userApiDelegate.usersPost(request);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(expectedResponse, response.getBody());
        verify(userService, times(1)).saveUser(request);
        verify(userService, times(1)).getUserByEmail(request.getEmail());
    }
}