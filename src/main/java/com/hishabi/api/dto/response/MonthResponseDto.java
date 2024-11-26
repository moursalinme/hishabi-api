package com.hishabi.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthResponseDto {

    private Integer id;

    private Long month;

    private Integer year;

    private Double balance;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
