package com.hishabi.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeResponseDto {

    private Long id;

    private Integer day;

    private String source;

    private Double amount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private PaymentMethodResponseDto paymentMethod;

}
