package com.hishabi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hishabi.api.entity.IncomeEntity;

public interface IncomeRepository extends JpaRepository<IncomeEntity, Long> {

}
