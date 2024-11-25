package com.hishabi.api.service;

import com.hishabi.api.dto.request.LoginRequestDto;
import com.hishabi.api.dto.request.UserRequestDto;
import com.hishabi.api.dto.response.UserResponseDto;

public interface AuthService {

    UserResponseDto handleRegister(UserRequestDto user);

    UserResponseDto handleLogin(LoginRequestDto user);

}
