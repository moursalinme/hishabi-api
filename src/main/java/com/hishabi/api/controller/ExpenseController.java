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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Expense API", description = "APIs for managing expense records")
public class ExpenseController {

    private final ExpenseService expenseService;

    /**
     * Creates a new expense record.
     *
     * @param expense The expense details to be created.
     * @return ResponseEntity containing the created expense.
     */
    @Operation(summary = "Create Expense Record", description = "Creates a new expense record. Amount must be greater than 0.")
    @PostMapping("/expense")
    public ResponseEntity<ApiResponse<ExpenseResponseDto>> createIncomeRecord(
            @RequestBody @Valid ExpenseRequestDto expense) {
        if (expense.getAmount() <= 0) {
            return ApiResponse.failure("Amount must be more than 0.", 400, null);
        }
        return ApiResponse.success(201, expenseService.createExpenseRecord(expense));
    }

    /**
     * Deletes an expense record by ID.
     *
     * @param id The ID of the expense record to delete.
     * @return ResponseEntity with a success message.
     */
    @Operation(summary = "Delete Expense Record", description = "Deletes an expense record based on the provided ID.")
    @DeleteMapping("/expense/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteIncomeById(@PathVariable Long id) {
        if (id <= 0) {
            return ApiResponse.failure("Invalid Id.", 400, null);
        }
        expenseService.deleteIncomeById(id);
        return ApiResponse.success("Deleted expense record with id: " + id, 200, null);
    }

    /**
     * Updates an existing expense record by ID.
     *
     * @param expense The updated expense details.
     * @param id      The ID of the expense to update.
     * @return ResponseEntity containing the updated expense.
     */
    @Operation(summary = "Update Expense Record", description = "Updates an expense record based on the provided ID and details.")
    @PatchMapping("/expense/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponseDto>> updateIncomeById(
            @RequestBody @Valid ExpenseRequestDto expense,
            @PathVariable Long id) {

        if (expense.getAmount() <= 0) {
            return ApiResponse.failure("Income must be more than 0.", 400, null);
        }

        return ApiResponse.success(200, expenseService.updateExpenseById(expense, id));
    }

    /**
     * Retrieves all expense records for a given month.
     *
     * @param monthId The ID of the month for which to retrieve expenses.
     * @return ResponseEntity containing a list of expenses.
     */
    @Operation(summary = "Retrieve Expenses by Month", description = "Fetches all expense records for a specific month.")
    @GetMapping("/expense/{monthId}")
    public ResponseEntity<ApiResponse<List<ExpenseResponseDto>>> getAllIncomesByMonthId(@PathVariable Long monthId) {
        if (monthId <= 0) {
            ApiResponse.failure("Invalid Month id.", 400, null);
        }
        return ApiResponse.success(200, expenseService.getAllExpenseByMonthId(monthId));
    }

}
