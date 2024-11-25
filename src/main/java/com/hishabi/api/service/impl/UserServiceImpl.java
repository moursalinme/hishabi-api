package com.hishabi.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hishabi.api.Mapper.Mapper;
import com.hishabi.api.dto.request.UserRequestDto;
import com.hishabi.api.dto.response.UserResponseDto;
import com.hishabi.api.entity.UserEntity;
import com.hishabi.api.repository.UserRepository;
import com.hishabi.api.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> Mapper.toUserResponseDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        return Mapper.toUserResponseDto(user.get());
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return Mapper.toUserResponseDto(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto user) {
        UserEntity userEntity = UserEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .createdAt(new Date())
                .build();
        return Mapper.toUserResponseDto(userRepository.save(userEntity));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
