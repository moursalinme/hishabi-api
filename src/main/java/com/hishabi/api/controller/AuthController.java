package com.hishabi.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hishabi.api.dto.request.CreateUserDto;
import com.hishabi.api.dto.response.ApiResponse;
import com.hishabi.api.dto.response.UserResponseDto;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> registerUser(@RequestBody CreateUserDto user) {
        // TODO: process POST request

        // return userDto;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDto>> handleLogin(@RequestBody LoginRequestDto user) {
        // TODO: process POST request

        return user;
    }

}
