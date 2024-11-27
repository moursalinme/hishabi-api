package com.hishabi.api.service;

import com.hishabi.api.dto.request.MonthRequestDto;
import com.hishabi.api.dto.response.MonthResponseDto;

public interface MonthService {

    MonthResponseDto createRecord(MonthRequestDto date);

    MonthResponseDto getSingleMonth(MonthRequestDto date);

    MonthResponseDto updateBalance(Double newBalance);

    void deleteRecordById(Long id);
}
