package com.hishabi.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResponse {
    private String jwt;
    UserResponseDto user;
}
