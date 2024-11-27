package com.hishabi.api.dto.request;

import jakarta.validation.constraints.Max;
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
public class MonthRequestDto {

    @NotNull(message = "Please provide a month.")
    @Min(value = 1, message = "Month cannot be less than 1.")
    @Max(value = 12, message = "Month cannot exceed 12.")
    private Integer month;

    @NotNull(message = "Please provide a year.")
    @Min(value = 2001, message = "Year cannot be less than 2001.")
    @Max(value = 2100, message = "Year cannot exceed 2100.")
    private Integer year;
}
