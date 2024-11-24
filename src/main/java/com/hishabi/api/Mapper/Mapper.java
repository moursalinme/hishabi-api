package com.hishabi.api.Mapper;

import com.hishabi.api.dto.response.UserResponseDto;
import com.hishabi.api.entity.UserEntity;

public class Mapper {

    public static UserResponseDto toUserResponseDto(UserEntity user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
