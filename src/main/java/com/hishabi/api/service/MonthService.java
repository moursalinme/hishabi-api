package com.hishabi.api.service;

import java.util.List;

import com.hishabi.api.dto.request.MonthRequestDto;
import com.hishabi.api.dto.response.MonthResponseDto;

public interface MonthService {

    MonthResponseDto createRecord(MonthRequestDto date);

    MonthResponseDto getSingleMonth(MonthRequestDto date);

    MonthResponseDto updateBalance(Long id, Double newBalance);

    MonthResponseDto getRecordById(Long id);

    List<MonthResponseDto> getAllRecordsByPrincipal();

    void deleteRecordById(Long id);
}
