package com.hishabi.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import jakarta.persistence.EntityNotFoundException;
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
        UserResponseDto user = userService.getPrincipleUserDto();

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
    @Transactional
    public void deleteRecordById(Long id) {
        Optional<MonthEntity> monthlyentity = monthRepository.findById(id);
        if (!monthlyentity.isPresent()) {
            throw new EntityNotFoundException("No records exits with that id.");
        }
        UserResponseDto user = userService.getPrincipleUserDto();
        if (monthlyentity.get().getUser().getId() != user.getId()) {
            throw new EntityNotFoundException("No records found with this id for this user.");
        }
        monthRepository.deleteById(id);
    }

    @Override
    public MonthResponseDto getSingleMonth(MonthRequestDto date) {
        UserResponseDto user = userService.getPrincipleUserDto();

        if (!monthRepository.existsByUser_IdAndMonthAndYear(user.getId(), date.getMonth(), date.getYear())) {
            throw new RuntimeException("No record created by you this month of the year.");
        }
        MonthEntity monthEntity = monthRepository.findByUser_IdAndMonthAndYear(user.getId(), date.getMonth(),
                date.getYear());
        return Mapper.toMonthResponseDto(monthEntity);
    }

    @Override
    public List<MonthResponseDto> getAllRecordsByPrincipal() {
        UserResponseDto user = userService.getPrincipleUserDto();

        return monthRepository.findAllByUser_id(user.getId())
                .stream()
                .map(Mapper::toMonthResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public MonthResponseDto getRecordById(Long id) {
        Optional<MonthEntity> monthEntity = monthRepository.findById(id);
        if (!monthEntity.isPresent()) {
            throw new EntityNotFoundException("Record Does not exist.");
        }
        UserResponseDto user = userService.getPrincipleUserDto();

        if (user.getId() != monthEntity.get().getUser().getId()) {
            throw new RuntimeException("Access denied. You are unauthorized to access this record.");
        }

        return Mapper.toMonthResponseDto(monthEntity.get());
    }

}
