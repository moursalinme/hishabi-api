package com.hishabi.api.service;

import java.util.List;

import com.hishabi.api.dto.request.IncomeRequestDto;
import com.hishabi.api.dto.response.IncomeResponseDto;

public interface IncomeService {

    IncomeResponseDto createIncomeRecord(IncomeRequestDto data);

    IncomeResponseDto updateIncomeById(IncomeRequestDto newIncomeData, Long id);

    List<IncomeResponseDto> getAllIncomesByMonthId(Long monthId);

    void deleteIncomeById(Long id);

}
