package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByCodeAndCustomerId(String code, Long id);
}
