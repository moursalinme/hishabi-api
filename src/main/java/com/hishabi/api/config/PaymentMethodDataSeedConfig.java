package com.hishabi.api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.hishabi.api.service.PaymentMethodService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(1)
public class PaymentMethodDataSeedConfig implements CommandLineRunner {

    private final PaymentMethodService paymentMethodService;

    @Override
    public void run(String... args) throws Exception {
        paymentMethodService.addPaymentMethod("BANK");
        paymentMethodService.addPaymentMethod("CARD");
        paymentMethodService.addPaymentMethod("CASH");
        paymentMethodService.addPaymentMethod("MOBILE BANK");
        paymentMethodService.addPaymentMethod("OTHER");
    }
}
