package com.javaweb.repository;

import com.javaweb.entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<AreaEntity, Integer>{
    void deleteByIdIn(List<Integer> ids);
}
