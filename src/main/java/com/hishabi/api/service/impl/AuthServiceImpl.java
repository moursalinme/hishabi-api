package com.hishabi.api.service.impl;

import org.springframework.stereotype.Service;

import com.hishabi.api.dto.request.LoginRequestDto;
import com.hishabi.api.dto.request.UserRequestDto;
import com.hishabi.api.dto.response.UserResponseDto;
import com.hishabi.api.service.AuthService;
import com.hishabi.api.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public UserResponseDto handleRegister(UserRequestDto user) {
        return userService.createUser(user);
    }

    @Override
    public UserResponseDto handleLogin(LoginRequestDto user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleLogin'");
    }

}
