package com.hishabi.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hishabi.api.Mapper.Mapper;
import com.hishabi.api.dto.response.PaymentMethodResponseDto;
import com.hishabi.api.entity.PaymentMethodEntity;
import com.hishabi.api.repository.PaymentMethodRepository;
import com.hishabi.api.service.PaymentMethodService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethodResponseDto> getAllPaymentMethods() {
        return paymentMethodRepository.findAll()
                .stream()
                .map(Mapper::toPaymentMethodResponseDto)
                .collect(Collectors.toList());

    }

    @Override
    public PaymentMethodResponseDto addPaymentMethod(String name) {
        PaymentMethodEntity paymentMethodEntity = PaymentMethodEntity.builder().name(name).build();
        return Mapper.toPaymentMethodResponseDto(paymentMethodRepository.save(paymentMethodEntity));
    }

}
