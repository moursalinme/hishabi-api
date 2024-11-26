package com.hishabi.api.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hishabi.api.Mapper.Mapper;
import com.hishabi.api.dto.request.MonthRequestDto;
import com.hishabi.api.dto.response.MonthResponseDto;
import com.hishabi.api.dto.response.UserResponseDto;
import com.hishabi.api.entity.MonthEntity;
import com.hishabi.api.entity.UserEntity;
import com.hishabi.api.repository.MonthRepository;
import com.hishabi.api.service.MonthService;
import com.hishabi.api.service.UserService;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MonthServiceImpl implements MonthService {

    private final Logger logger = LoggerFactory.getLogger(MonthServiceImpl.class);
    private final UserService userService;
    private final MonthRepository monthRepository;
    private final EntityManager entityManager;

    @Override
    public MonthResponseDto createRecord(MonthRequestDto date) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("From security Context: {}", authentication.toString());

        UserResponseDto user = userService.getUserByEmail(authentication.getPrincipal().toString());

        if (monthRepository.existsByUser_IdAndMonthAndYear(user.getId(), date.getMonth(), date.getYear())) {
            throw new RuntimeException("Can not create record. Already Exists for this month.");
        }

        UserEntity userEntity = entityManager.getReference(UserEntity.class, user.getId());

        MonthEntity monthEntity = MonthEntity.builder()
                .user(userEntity)
                .month(date.getMonth())
                .year(date.getYear())
                .balance(0.0)
                .updatedAt(LocalDateTime.now())
                .build();

        logger.info("Before {}", monthEntity);
        monthEntity = monthRepository.save(monthEntity);
        logger.info("After {}", monthEntity);

        return Mapper.toMonthResponseDto(monthEntity);
    }

    @Override
    public MonthResponseDto updateBalance(Double newBalance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBalance'");
    }

    @Override
    public void deleteRecord(MonthRequestDto date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecord'");
    }

}