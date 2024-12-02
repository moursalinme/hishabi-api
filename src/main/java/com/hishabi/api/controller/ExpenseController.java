package com.hishabi.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hishabi.api.dto.request.ExpenseRequestDto;
import com.hishabi.api.dto.response.ApiResponse;
import com.hishabi.api.dto.response.ExpenseResponseDto;
import com.hishabi.api.service.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/expense")
    public ResponseEntity<ApiResponse<ExpenseResponseDto>> createIncomeRecord(
            @RequestBody @Valid ExpenseRequestDto expense) {
        if (expense.getAmount() <= 0) {
            return ApiResponse.failure("Amount must be more than 0.", 400, null);
        }
        return ApiResponse.success(201, expenseService.createExpenseRecord(expense));
    }

    @DeleteMapping("/expense/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteIncomeById(@PathVariable Long id) {
        if (id <= 0) {
            return ApiResponse.failure("Invalid Id.", 400, null);
        }
        expenseService.deleteIncomeById(id);
        return ApiResponse.success("Deleted expense record with id: " + id, 200, null);
    }

    @PatchMapping("/expense/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponseDto>> updateIncomeById(
            @RequestBody @Valid ExpenseRequestDto expense,
            @PathVariable Long id) {

        if (expense.getAmount() <= 0) {
            return ApiResponse.failure("Income must be more than 0.", 400, null);
        }

        return ApiResponse.success(200, expenseService.updateExpenseById(expense, id));
    }

    @GetMapping("/expense/{monthId}")
    public ResponseEntity<ApiResponse<List<ExpenseResponseDto>>> getAllIncomesByMonthId(@PathVariable Long monthId) {
        if (monthId <= 0) {
            ApiResponse.failure("Invalid Month id.", 400, null);
        }
        return ApiResponse.success(200, expenseService.getAllExpenseByMonthId(monthId));
    }

}
