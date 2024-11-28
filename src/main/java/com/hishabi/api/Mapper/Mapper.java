package com.hishabi.api.Mapper;

import com.hishabi.api.dto.response.IncomeResponseDto;
import com.hishabi.api.dto.response.MonthResponseDto;
import com.hishabi.api.dto.response.PaymentMethodResponseDto;
import com.hishabi.api.dto.response.UserResponseDto;
import com.hishabi.api.entity.IncomeEntity;
import com.hishabi.api.entity.MonthEntity;
import com.hishabi.api.entity.PaymentMethodEntity;
import com.hishabi.api.entity.UserEntity;

public class Mapper {

    public static UserResponseDto toUserResponseDto(UserEntity user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .role(user.getRole())
                .build();
    }

    public static MonthResponseDto toMonthResponseDto(MonthEntity month) {
        return MonthResponseDto.builder()
                .id(month.getId())
                .month(month.getMonth())
                .year(month.getYear())
                .balance(month.getBalance())
                .createdAt(month.getCreatedAt())
                .updatedAt(month.getUpdatedAt())
                .build();
    }

    public static PaymentMethodResponseDto toPaymentMethodResponseDto(PaymentMethodEntity payment) {
        return PaymentMethodResponseDto.builder()
                .id(payment.getId())
                .name(payment.getName())
                .build();
    }

    public static IncomeResponseDto toIncomeResponseDto(IncomeEntity income) {
        return IncomeResponseDto.builder()
                .id(income.getId())
                .source(income.getSource())
                .amount(income.getAmount())
                .paymentMethod(toPaymentMethodResponseDto(income.getPaymentMethod()))
                .createdAt(income.getCreatedAt())
                .updatedAt(income.getUpdatedAt())
                .build();
    }
}
