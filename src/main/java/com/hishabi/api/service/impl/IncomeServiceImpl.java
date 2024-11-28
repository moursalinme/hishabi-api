package com.hishabi.api.service.impl;

import org.springframework.stereotype.Service;

import com.hishabi.api.dto.request.IncomeRequestDto;
import com.hishabi.api.dto.response.IncomeResponseDto;
import com.hishabi.api.repository.IncomeRepository;
import com.hishabi.api.service.IncomeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    @Override
    public IncomeResponseDto createIncomeRecord(IncomeRequestDto data) {
        throw new UnsupportedOperationException("Unimplemented method 'createIncomeRecord'");
    }

    @Override
    public void DeleteIncomeById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DeleteIncomeById'");
    }

}
