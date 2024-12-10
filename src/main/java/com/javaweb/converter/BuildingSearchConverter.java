package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BuildingSearchConverter {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingSearchResponse toBuildingResponse(BuildingEntity buildingEntite){
        String areas= buildingEntite.getAreas().stream().map(item->item.getValue().toString()).sorted().collect(Collectors.joining(","));
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntite,BuildingSearchResponse.class);
        buildingSearchResponse.setAddress(buildingEntite.getStreet() + ", " + buildingEntite.getWard() + ", " + districtCode.getDistrictName(buildingEntite.getDistrict()));
        buildingSearchResponse.setRentArea(areas);
        return buildingSearchResponse;
    }
}
