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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller for managing month records.
 * Provides endpoints for creating, retrieving, and deleting month records.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Month Records", description = "API for managing month records")
public class MonthController {

    private final MonthService monthService;
    private final Logger logger = LoggerFactory.getLogger(MonthController.class);

    /**
     * Retrieves a single month record for the authenticated user based on the
     * provided date.
     *
     * @param date the request body containing month and year details
     * @return a ResponseEntity containing the retrieved MonthResponseDto wrapped in
     *         an ApiResponse
     */
    @Operation(summary = "Get a single month record", description = "Fetches a single month record for the authenticated user based on the provided date.")

    @PostMapping("/month-records/single")
    public ResponseEntity<ApiResponse<MonthResponseDto>> getSingleRecord(
            @Valid @RequestBody MonthRequestDto date) {

        // logger.info("date provided: {}", date);
        MonthResponseDto response;
        try {
            response = monthService.getSingleMonth(date);
        } catch (Exception ex) {
            return ApiResponse.failure(ex.getMessage(), 403, null);
        }
        return ApiResponse.success(201, response);
    }

    /**
     * Retrieves a month record by its unique ID.
     *
     * @param id the ID of the month record
     * @return a ResponseEntity containing the retrieved MonthResponseDto wrapped in
     *         an ApiResponse
     */
    @Operation(summary = "Get a month record by ID", description = "Fetches a month record by its unique ID.")

    @GetMapping("/month-records/{id}")
    public ResponseEntity<ApiResponse<MonthResponseDto>> getRecordById(@PathVariable Long id) {
        MonthResponseDto response;
        try {
            response = monthService.getRecordById(id);
        } catch (Exception ex) {
            return ApiResponse.failure(ex.getMessage(), 403, null);
        }
        return ApiResponse.success(200, response);
    }

    /**
     * Creates a new month record for the authenticated user.
     *
     * @param date the request body containing month and year details
     * @return a ResponseEntity containing the created MonthResponseDto wrapped in
     *         an ApiResponse
     */
    @Operation(summary = "Create a new month record", description = "Creates a new month record for the authenticated user.")

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

    /**
     * Deletes a month record by its unique ID.
     *
     * @param id the ID of the month record to be deleted
     * @return a ResponseEntity indicating success or failure of the deletion
     */
    @Operation(summary = "Delete a month record by ID", description = "Deletes a month record by its unique ID.")

    @DeleteMapping("/month-records/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteRecord(@PathVariable Long id) {
        try {
            monthService.deleteRecordById(id);
        } catch (EntityNotFoundException ex) {
            return ApiResponse.failure(ex.getMessage(), 404, null);
        }
        return ApiResponse.success("Month Record with id: " + id + " is deleted.", 200, null);
    }

    /**
     * Retrieves all month records for the currently authenticated user.
     *
     * @return a ResponseEntity containing a list of MonthResponseDto wrapped in an
     *         ApiResponse
     */
    @Operation(summary = "Get all month records for the user", description = "Fetches all month records for the currently authenticated user.")

    @GetMapping("/month-records/all")
    public ResponseEntity<ApiResponse<List<MonthResponseDto>>> getAllRecords() {
        return ApiResponse.success(200, monthService.getAllRecordsByPrincipal());
    }
}
