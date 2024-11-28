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

import com.hishabi.api.dto.request.IncomeRequestDto;
import com.hishabi.api.dto.response.ApiResponse;
import com.hishabi.api.dto.response.IncomeResponseDto;
import com.hishabi.api.service.IncomeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Income Management", description = "APIs for managing income records")
public class IncomeController {

    private final IncomeService incomeService;
    // private final Logger logger =
    // LoggerFactory.getLogger(IncomeController.class);

    /**
     * Create a new income record.
     * 
     * @param income The income data provided in the request body.
     * @return A ResponseEntity containing the created income record and a success
     *         message.
     */
    @Operation(summary = "Create a new income record", description = "This endpoint creates a new income record in the system.")
    @PostMapping("/income")
    public ResponseEntity<ApiResponse<IncomeResponseDto>> createIncomeRecord(
            @RequestBody @Valid IncomeRequestDto income) {
        if (income.getAmount() <= 0) {
            return ApiResponse.failure("Income must be more than 0.", 400, null);
        }
        return ApiResponse.success(201, incomeService.createIncomeRecord(income));
    }

    /**
     * Delete an income record by its ID.
     * 
     * @param id The ID of the income record to delete.
     * @return A ResponseEntity with a success message if the deletion was
     *         successful.
     */
    @Operation(summary = "Delete an income record", description = "This endpoint deletes an existing income record by its ID.")
    @DeleteMapping("/income/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteIncomeById(@PathVariable Long id) {
        if (id <= 0) {
            return ApiResponse.failure("Invalid Id.", 400, null);
        }
        incomeService.deleteIncomeById(id);
        return ApiResponse.success("Deleted Income record with id: " + id, 200, null);
    }

    /**
     * Update an income record by its ID.
     * 
     * @param income The updated income data provided in the request body.
     * @param id     The ID of the income record to update.
     * @return A ResponseEntity containing the updated income record and a success
     *         message.
     */
    @Operation(summary = "Update an income record", description = "This endpoint updates an existing income record by its ID.")
    @PatchMapping("/income/{id}")
    public ResponseEntity<ApiResponse<IncomeResponseDto>> updateIncomeById(@RequestBody @Valid IncomeRequestDto income,
            @PathVariable Long id) {

        if (income.getAmount() <= 0) {
            return ApiResponse.failure("Income must be more than 0.", 400, null);
        }

        return ApiResponse.success(200, incomeService.updateIncomeById(income, id));
    }

    /**
     * Retrieve all income records for a specific month.
     * 
     * @param monthId The ID of the month to retrieve income records for.
     * @return A ResponseEntity containing a list of income records for the
     *         specified month.
     */
    @Operation(summary = "Get all incomes by month", description = "This endpoint retrieves all income records for a specific month.")
    @GetMapping("/income/{monthId}")
    public ResponseEntity<ApiResponse<List<IncomeResponseDto>>> getAllIncomesByMonthId(@PathVariable Long monthId) {
        if (monthId <= 0) {
            ApiResponse.failure("Invalid Month id.", 400, null);
        }
        return ApiResponse.success(200, incomeService.getAllIncomesByMonthId(monthId));
    }

}
