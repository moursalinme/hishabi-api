package com.hishabi.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hishabi.api.dto.request.IncomeRequestDto;
import com.hishabi.api.dto.response.ApiResponse;
import com.hishabi.api.dto.response.IncomeResponseDto;
import com.hishabi.api.service.IncomeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class IncomeController {

    private final IncomeService incomeService;
    // private final Logger logger =
    // LoggerFactory.getLogger(IncomeController.class);

    @PostMapping("/income")
    public ResponseEntity<ApiResponse<IncomeResponseDto>> createIncomeRecord(
            @RequestBody @Valid IncomeRequestDto income) {
        if (income.getAmount() <= 0) {
            return ApiResponse.failure("Income must be more than 0.", 400, null);
        }
        return ApiResponse.success(201, incomeService.createIncomeRecord(income));
    }

    @DeleteMapping("/income/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteIncomeById(@PathVariable Long id) {
        if (id <= 0) {
            return ApiResponse.failure("Invalid Id.", 400, null);
        }
        incomeService.deleteIncomeById(id);
        return ApiResponse.success(200, null);
    }

    @PatchMapping("/income/{id}")
    public ResponseEntity<ApiResponse<IncomeResponseDto>> updateIncomeById(@RequestBody @Valid IncomeRequestDto income,
            @PathVariable Long id) {

        if (income.getAmount() <= 0) {
            return ApiResponse.failure("Income must be more than 0.", 400, null);
        }

        return ApiResponse.success(200, incomeService.updateIncomeById(income, id));
    }

}
