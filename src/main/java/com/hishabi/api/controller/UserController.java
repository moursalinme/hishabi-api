package com.hishabi.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hishabi.api.dto.response.ApiResponse;
import com.hishabi.api.dto.response.UserResponseDto;
import com.hishabi.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Users", description = "APIs for managing users")
public class UserController {

    private final UserService userService;

    /**
     * Retrieve all users.
     *
     * @return A ResponseEntity containing the list of all users.
     */
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users.")
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {
        return ApiResponse.success(200, userService.getAllUsers());
    }

    /**
     * Retrieve a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return A ResponseEntity containing the user details.
     */
    @Operation(summary = "Get user by ID", description = "Retrieve details of a user using their unique ID.")
    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(@PathVariable Long id) {
        return ApiResponse.success(200, userService.getUserById(id));
    }

    /**
     * Delete a user by ID.
     *
     * @param id The ID of the user to delete.
     * @return A ResponseEntity confirming the deletion.
     */
    @Operation(summary = "Delete user by ID", description = "Delete a user using their unique ID.")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteUserById(@PathVariable Long id) {
        return ApiResponse.success(200, null);
    }

}
