package com.hishabi.api.controller;

import org.springframework.http.ResponseEntity;
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

    @PostMapping("/income")
    public ResponseEntity<ApiResponse<IncomeResponseDto>> createIncomeRecord(
            @RequestBody @Valid IncomeRequestDto income) {
        return ApiResponse.success(201, incomeService.createIncomeRecord(income));
    }

}
