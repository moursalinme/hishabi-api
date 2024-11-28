package com.hishabi.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hishabi.api.dto.response.ApiResponse;
import com.hishabi.api.dto.response.PaymentMethodResponseDto;
import com.hishabi.api.service.PaymentMethodService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Payment Methods", description = "APIs for retrieving payment method data")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    /**
     * Retrieve all available payment methods.
     *
     * @return A ResponseEntity containing the list of all payment methods.
     */
    @Operation(summary = "Get all payment methods", description = "This endpoint retrieves a list of all available payment methods.")
    @GetMapping("/payment-methods")
    public ResponseEntity<ApiResponse<List<PaymentMethodResponseDto>>> getAllPaymentMethods() {
        return ApiResponse.success(200, paymentMethodService.getAllPaymentMethods());
    }

}
