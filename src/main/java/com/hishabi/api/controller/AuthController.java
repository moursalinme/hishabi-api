package com.hishabi.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hishabi.api.dto.request.LoginRequestDto;
import com.hishabi.api.dto.request.UserRequestDto;
import com.hishabi.api.dto.response.ApiResponse;
import com.hishabi.api.dto.response.UserResponseDto;
import com.hishabi.api.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> registerUser(@RequestBody @Valid UserRequestDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        UserResponseDto userResponseDto = authService.handleRegister(user);
        return ApiResponse.success(201, userResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDto>> handleLogin(@RequestBody @Valid LoginRequestDto user) {
        // TODO: process POST request
        return ApiResponse.failure("Route not implemented yet.", 500, null);
    }

}
