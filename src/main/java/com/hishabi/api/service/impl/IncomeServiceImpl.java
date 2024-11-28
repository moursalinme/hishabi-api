package com.hishabi.api.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hishabi.api.Mapper.Mapper;
import com.hishabi.api.dto.request.IncomeRequestDto;
import com.hishabi.api.dto.response.IncomeResponseDto;
import com.hishabi.api.entity.IncomeEntity;
import com.hishabi.api.entity.MonthEntity;
import com.hishabi.api.entity.PaymentMethodEntity;
import com.hishabi.api.repository.IncomeRepository;
import com.hishabi.api.service.IncomeService;
import com.hishabi.api.service.MonthService;
import com.hishabi.api.service.PaymentMethodService;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final PaymentMethodService paymentMethodService;
    private final MonthService monthService;
    private final EntityManager entityManager;
    // private final Logger logger =
    // LoggerFactory.getLogger(IncomeServiceImpl.class);

    @Override
    @Transactional
    public IncomeResponseDto createIncomeRecord(IncomeRequestDto data) {
        if (!paymentMethodService.existById(data.getPaymentMethodId())) {
            throw new RuntimeException("Invalid Payment method.");
        }

        IncomeEntity incomeEntity = IncomeEntity.builder()
                .month(entityManager.getReference(MonthEntity.class, data.getMonthId()))
                .source(data.getSource())
                .paymentMethod(entityManager.getReference(PaymentMethodEntity.class, data.getPaymentMethodId()))
                .amount(data.getAmount())
                .build();

        monthService.getRecordById(data.getMonthId());
        monthService.updateBalance(data.getMonthId(), data.getAmount());

        incomeEntity = incomeRepository.save(incomeEntity);

        incomeEntity.setCreatedAt(LocalDateTime.now());
        incomeEntity.setUpdatedAt(LocalDateTime.now());
        return Mapper.toIncomeResponseDto(incomeEntity);
    }

    @Override
    public void DeleteIncomeById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DeleteIncomeById'");
    }

}
