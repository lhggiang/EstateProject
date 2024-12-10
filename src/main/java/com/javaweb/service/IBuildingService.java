package com.javaweb.service;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingAssignmentRequest;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    void deleteBuilding(Long id);
    void saveOrUpdate(BuildingDTO buildingDTO);
    BuildingDTO findByID(Long id);
    void save(BuildingAssignmentRequest request);
    List<BuildingSearchResponse> getBuildings(BuildingSearchRequest buildingSearchRequest, Pageable pageable);
    ResponseDTO listTypeCode(Long buildingId);
}