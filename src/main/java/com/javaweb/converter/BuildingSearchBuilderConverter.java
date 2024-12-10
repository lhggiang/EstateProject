package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.request.BuildingSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder builderSearchBuilder = new BuildingSearchBuilder.BuildingBuilder()
                .setName(buildingSearchRequest.getName())
                .setFloorArea (buildingSearchRequest.getFloorArea())
                .setWard (buildingSearchRequest.getWard())
                .setStreet (buildingSearchRequest.getStreet())
                .setDistrict (buildingSearchRequest.getDistrict())
                .setNumberOfBasement (buildingSearchRequest.getNumberOfBasement())
                .setTypeCode(buildingSearchRequest.getTypeCode())
                .setManagerName (buildingSearchRequest.getManagerName())
                .setManagerPhone(buildingSearchRequest.getManagerPhone())
                .setRentPriceTo(buildingSearchRequest.getRentPriceTo())
                .setRentPriceFrom(buildingSearchRequest.getRentPriceFrom())
                .setRentAreaFrom(buildingSearchRequest.getRentAreaFrom())
                .setRentAreaTo(buildingSearchRequest.getRentAreaTo())
                .setStaffId(buildingSearchRequest.getStaffId())
                .setLevel(buildingSearchRequest.getLevel())
                .setDirection(buildingSearchRequest.getDirection())
                .build();
        return builderSearchBuilder;
    }
}
