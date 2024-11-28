package com.hishabi.api.service;

import java.util.List;

import com.hishabi.api.dto.response.PaymentMethodResponseDto;

public interface PaymentMethodService {

    List<PaymentMethodResponseDto> getAllPaymentMethods();

    PaymentMethodResponseDto addPaymentMethod(String name);

    boolean existById(Long id);
}
