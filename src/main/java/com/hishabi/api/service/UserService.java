package com.hishabi.api.service;

import java.util.List;

import com.hishabi.api.dto.request.CreateUserDto;
import com.hishabi.api.dto.response.UserResponseDto;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    UserResponseDto getUserByEmail(String email);

    UserResponseDto createUser(CreateUserDto user);

    void deleteUserById(Long id);

}
