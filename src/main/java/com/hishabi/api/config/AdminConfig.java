package com.hishabi.api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hishabi.api.entity.UserEntity;
import com.hishabi.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        UserEntity admin = UserEntity.builder()
                .firstName("Admin")
                .lastName("Hishab-Rakhun")
                .email("admin@hishabiApi.com")
                .password(passwordEncoder.encode("apiadmin"))
                .role("ROLE_ADMIN")
                .build();

        if (userRepository.findByEmail(admin.getEmail()) == null) {
            userRepository.save(admin);
        }
    }

}
