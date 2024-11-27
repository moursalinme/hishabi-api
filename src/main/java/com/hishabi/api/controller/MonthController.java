package com.hishabi.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hishabi.api.dto.request.MonthRequestDto;
import com.hishabi.api.dto.response.ApiResponse;
import com.hishabi.api.dto.response.MonthResponseDto;
import com.hishabi.api.service.MonthService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MonthController {

    private final MonthService monthService;
    private final Logger logger = LoggerFactory.getLogger(MonthController.class);

    @PostMapping("/month-records/single")
    public ResponseEntity<ApiResponse<MonthResponseDto>> getSingleRecord(
            @Valid @RequestBody MonthRequestDto date) {

        // logger.info("date provided: {}", date);
        MonthResponseDto response;
        try {
            response = monthService.getSingleMonth(date);
        } catch (Exception ex) {
            return ApiResponse.failure(ex.getMessage(), 409, null);
        }
        return ApiResponse.success(201, response);
    }

    @PostMapping("/month-records")
    public ResponseEntity<ApiResponse<MonthResponseDto>> createRecord(
            @Valid @RequestBody MonthRequestDto date) {
        // logger.info("date provided: {}", date);
        MonthResponseDto response;
        try {
            response = monthService.createRecord(date);
        } catch (Exception ex) {
            return ApiResponse.failure(ex.getMessage(), 409, null);
        }
        return ApiResponse.success(201, response);
    }

    @DeleteMapping("/month-records/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteRecord(@PathVariable Long id) {
        try {
            monthService.deleteRecordById(id);
        } catch (EntityNotFoundException ex) {
            return ApiResponse.failure(ex.getMessage(), 404, null);
        }
        return ApiResponse.success("Month Record with id: " + id + " is deleted.", 200, null);
    }

    @GetMapping("/month-records/all")
    public ResponseEntity<ApiResponse<List<MonthResponseDto>>> getAllRecords() {
        return ApiResponse.success(200, monthService.getAllRecordsByPrincipal());
    }
}
