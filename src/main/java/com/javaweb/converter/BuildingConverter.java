package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingEntity convertToBuildingEntity(BuildingDTO buildingDTO){
        BuildingEntity mapBuilding = modelMapper.map(buildingDTO,BuildingEntity.class);
        String typeCode = buildingDTO.getTypeCode().stream().collect(Collectors.joining(","));
        mapBuilding.setTypeCode(typeCode);
        return mapBuilding;
    }
}
