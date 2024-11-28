package com.hishabi.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeRequestDto {

    @NotNull(message = "Month Id must be provided.")
    private Long monthId;

    private String source;

    @NotNull(message = "Please enter an ammount.")
    @Min(value = 1, message = "Amount must be at least 1")
    private Double amount;

    @NotNull(message = "payment method type id must be provided.")
    private Long paymentMethodId;

}
