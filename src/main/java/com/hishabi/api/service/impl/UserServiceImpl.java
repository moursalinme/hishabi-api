package com.hishabi.api.service.impl;

import org.springframework.stereotype.Service;

import com.hishabi.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;

}
