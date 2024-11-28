package com.hishabi.api.service;

import com.hishabi.api.dto.request.IncomeRequestDto;
import com.hishabi.api.dto.response.IncomeResponseDto;

public interface IncomeService {

    IncomeResponseDto createIncomeRecord(IncomeRequestDto data);

    void DeleteIncomeById(Long id);

}
