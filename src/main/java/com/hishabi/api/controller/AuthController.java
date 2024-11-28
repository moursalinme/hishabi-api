package com.hishabi.api.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for user registration and login")
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Register a new user.
     *
     * @param user The user data provided in the request body.
     * @return A ResponseEntity containing the created user details and a JWT token.
     */
    @Operation(summary = "Register a new user", description = "This endpoint allows a user to register and returns their details along with a JWT token.")
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

    /**
     * Authenticate a user and return a JWT token.
     *
     * @param loginReq The login credentials provided in the request body.
     * @return A ResponseEntity containing the JWT token and user details upon
     *         successful login.
     */
    @Operation(summary = "Login a user", description = "This endpoint authenticates a user and returns a JWT token along with their details.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> handleLogin(@RequestBody @Valid LoginRequestDto loginReq) {
        UserResponseDto user;
        try {
            user = authService.handleLogin(loginReq);
        } catch (UsernameNotFoundException ex) {
            throw new BadCredentialsException("Invalid email or password.");
        }
        HashMap<String, Object> token = new HashMap<>();
        token.put("jwt", jwtService.generateToken(user));
        token.put("user", user);
        return ApiResponse.success("User Login successful.", HttpStatus.OK.value(),
                token);
    }

}
