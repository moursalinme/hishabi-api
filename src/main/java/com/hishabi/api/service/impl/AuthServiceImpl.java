package com.hishabi.api.service.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
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
        UserResponseDto userResponse = userService.createUser(user);
        addPrincipalToSecurityContext(userResponse);
        return userResponse;
    }

    @Override
    public UserResponseDto handleLogin(LoginRequestDto loginReq) {
        UserResponseDto user = userService.authenticateUser(loginReq);
        addPrincipalToSecurityContext(user);
        return user;
    }

    private void addPrincipalToSecurityContext(UserResponseDto user) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole()));

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
