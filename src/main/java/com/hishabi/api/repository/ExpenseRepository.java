package com.hishabi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hishabi.api.entity.ExpenseEntity;
import com.hishabi.api.entity.IncomeEntity;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    List<IncomeEntity> findAllByMonth_id(Long monthId);

}
