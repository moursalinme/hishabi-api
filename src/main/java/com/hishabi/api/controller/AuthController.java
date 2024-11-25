package com.hishabi.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hishabi.api.config.Jwt.JwtService;
import com.hishabi.api.dto.request.LoginRequestDto;
import com.hishabi.api.dto.request.UserRequestDto;
import com.hishabi.api.dto.response.ApiResponse;
import com.hishabi.api.dto.response.RegistrationResponse;
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
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegistrationResponse>> registerUser(@RequestBody @Valid UserRequestDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        UserResponseDto userResponseDto = authService.handleRegister(user);
        String jwt = jwtService.generateToken(userResponseDto);
        RegistrationResponse response = RegistrationResponse.builder()
                .jwt(jwt)
                .user(userResponseDto)
                .build();
        return ApiResponse.success(201, response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDto>> handleLogin(@RequestBody @Valid LoginRequestDto user) {
        // TODO: process POST request
        return ApiResponse.failure("Route not implemented yet.", 500, null);
    }

}
