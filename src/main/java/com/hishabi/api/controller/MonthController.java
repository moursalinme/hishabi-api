package com.hishabi.api.controller;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hishabi.api.dto.request.MonthRequestDto;
import com.hishabi.api.dto.response.ApiResponse;
import com.hishabi.api.dto.response.MonthResponseDto;
import com.hishabi.api.service.MonthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MonthController {

    private final MonthService monthService;
    private final Logger logger = LoggerFactory.getLogger(MonthController.class);

    @GetMapping("/month-records")
    public ResponseEntity<ApiResponse<MonthResponseDto>> getSingleRecord(
            @RequestBody @Valid MonthRequestDto date) {
        throw new NotImplementedException("not implemented yet.");
    }

    @PostMapping("/month-records")
    public ResponseEntity<ApiResponse<MonthResponseDto>> createRecord(
            @Valid @RequestBody MonthRequestDto date) {
        logger.info("date provided: {}", date);
        MonthResponseDto response;
        try {
            response = monthService.createRecord(date);
        } catch (Exception ex) {
            return ApiResponse.failure(ex.getMessage(), 409, null);
        }
        return ApiResponse.success(201, response);
    }

    @DeleteMapping("/month-records")
    public ResponseEntity<ApiResponse<Object>> deleteRecord(
            @RequestParam @Valid MonthRequestDto date) {
        throw new NotImplementedException("not implemented yet.");
    }

    @GetMapping("/month-records/all")
    public ResponseEntity<ApiResponse<List<MonthResponseDto>>> getAllRecords() {
        throw new NotImplementedException("not implemented yet.");
    }
}
