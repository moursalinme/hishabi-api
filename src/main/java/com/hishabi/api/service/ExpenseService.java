package com.hishabi.api.service;

import java.util.List;

import com.hishabi.api.dto.request.ExpenseRequestDto;
import com.hishabi.api.dto.response.ExpenseResponseDto;

public interface ExpenseService {

    ExpenseResponseDto createExpenseRecord(ExpenseRequestDto data);

    ExpenseResponseDto updateExpenseById(ExpenseRequestDto reqData, Long id);

    List<ExpenseResponseDto> getAllExpenseByMonthId(Long monthId);

    void deleteIncomeById(Long id);

}
