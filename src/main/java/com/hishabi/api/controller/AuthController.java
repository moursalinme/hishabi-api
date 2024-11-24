package com.hishabi.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hishabi.api.dto.request.CreateUserDto;
import com.hishabi.api.dto.request.LoginRequestDto;
import com.hishabi.api.dto.response.ApiResponse;
import com.hishabi.api.dto.response.UserResponseDto;
import com.hishabi.api.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> registerUser(@RequestBody CreateUserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ApiResponse.success(201, userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDto>> handleLogin(@RequestBody LoginRequestDto user) {
        // TODO: process POST request

        return user;
    }

}
