package com.hishabi.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hishabi.api.dto.response.ApiResponse;
import com.hishabi.api.dto.response.PaymentMethodResponseDto;
import com.hishabi.api.service.PaymentMethodService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @GetMapping("/payment-methods")
    public ResponseEntity<ApiResponse<List<PaymentMethodResponseDto>>> getAllPaymentMethods() {
        return ApiResponse.success(200, paymentMethodService.getAllPaymentMethods());
    }

}
