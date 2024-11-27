package com.hishabi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hishabi.api.entity.MonthEntity;

@Repository
public interface MonthRepository extends JpaRepository<MonthEntity, Long> {

    boolean existsByUser_IdAndMonthAndYear(Long userId, Integer month, Integer year);

    MonthEntity findByUser_IdAndMonthAndYear(Long userId, Integer month, Integer year);

    List<MonthEntity> findAllByUser_id(Long userId);
}
