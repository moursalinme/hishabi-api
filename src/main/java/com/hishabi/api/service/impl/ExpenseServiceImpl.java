package com.hishabi.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hishabi.api.Mapper.Mapper;
import com.hishabi.api.dto.request.ExpenseRequestDto;
import com.hishabi.api.dto.response.ExpenseResponseDto;
import com.hishabi.api.entity.ExpenseEntity;
import com.hishabi.api.entity.MonthEntity;
import com.hishabi.api.entity.PaymentMethodEntity;
import com.hishabi.api.repository.ExpenseRepository;
import com.hishabi.api.service.ExpenseService;
import com.hishabi.api.service.MonthService;
import com.hishabi.api.service.PaymentMethodService;
import com.hishabi.api.service.UserService;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final PaymentMethodService paymentMethodService;
    private final MonthService monthService;
    private final EntityManager entityManager;
    private final UserService userService;

    @Override
    @Transactional
    public ExpenseResponseDto createExpenseRecord(ExpenseRequestDto reqData) {
        if (!paymentMethodService.existById(reqData.getPaymentMethodId())) {
            throw new RuntimeException("Invalid Payment method.");
        }

        ExpenseEntity expenseEntity = ExpenseEntity.builder()
                .month(entityManager.getReference(MonthEntity.class, reqData.getMonthId()))
                .day(reqData.getDay())
                .source(reqData.getSource())
                .paymentMethod(entityManager.getReference(PaymentMethodEntity.class, reqData.getPaymentMethodId()))
                .amount(reqData.getAmount())
                .build();

        monthService.getRecordById(reqData.getMonthId());
        monthService.updateBalance(reqData.getMonthId(), -reqData.getAmount());

        expenseEntity = expenseRepository.save(expenseEntity);

        expenseEntity.setCreatedAt(LocalDateTime.now());
        expenseEntity.setUpdatedAt(LocalDateTime.now());
        return Mapper.toExpenseResponseDto(expenseEntity);
    }

    @Override
    @Transactional
    public ExpenseResponseDto updateExpenseById(ExpenseRequestDto reqData, Long id) {
        ExpenseEntity expenseEntity = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No income record found with that id."));

        if (!expenseEntity.getMonth().getUser().getId().equals(userService.getPrincipleUserDto().getId())) {
            throw new RuntimeException("Access denied. This record is not yours to modify.");
        }

        if (reqData.getPaymentMethodId() != null
                && !reqData.getPaymentMethodId().equals(expenseEntity.getPaymentMethod().getId())
                && paymentMethodService.existById(reqData.getPaymentMethodId())) {
            expenseEntity.setPaymentMethod(entityManager
                    .getReference(PaymentMethodEntity.class, reqData.getPaymentMethodId()));
        } else {
            throw new RuntimeException("Payment id not valid.");
        }

        if (reqData.getAmount() != null && !reqData.getAmount().equals(expenseEntity.getAmount())) {
            monthService.updateBalance(expenseEntity.getMonth().getId(),
                    expenseEntity.getAmount() - reqData.getAmount());
            expenseEntity.setAmount(reqData.getAmount());
        }

        Optional.ofNullable(reqData.getSource())
                .ifPresent(expenseEntity::setSource);

        expenseEntity = expenseRepository.save(expenseEntity);
        expenseEntity.setUpdatedAt(LocalDateTime.now());
        return Mapper.toExpenseResponseDto(expenseEntity);
    }

    @Override
    public List<ExpenseResponseDto> getAllExpenseByMonthId(Long monthId) {
        monthService.getRecordById(monthId);

        return expenseRepository.findAllByMonth_id(monthId)
                .stream()
                .map(Mapper::toExpenseResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteIncomeById(Long id) {
        ExpenseEntity expenseEntity = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No records exists with that id."));

        if (!Objects.equals(userService.getPrincipleUserDto().getId(), expenseEntity.getMonth().getUser().getId())) {
            throw new RuntimeException("You do not have the authority to delete this record.");
        }

        monthService.updateBalance(expenseEntity.getMonth().getId(), expenseEntity.getAmount());
        expenseRepository.deleteById(id);
    }

}
