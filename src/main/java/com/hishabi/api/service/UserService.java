package com.hishabi.api.service;

import java.util.List;

import com.hishabi.api.dto.request.UserRequestDto;
import com.hishabi.api.dto.response.UserResponseDto;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    UserResponseDto getUserByEmail(String email);

    UserResponseDto createUser(UserRequestDto user);

    void deleteUserById(Long id);

}
