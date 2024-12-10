package com.javaweb.converter;
import com.javaweb.entity.AreaEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class RentAreaConverter {
    public List<AreaEntity> convertToRentAreaEntity(BuildingDTO buildingDTO, BuildingEntity mapBuilding){
        String[] arr = buildingDTO.getRentArea().split(",");
        List<AreaEntity> listAreaEntity = new ArrayList<>();
        for(String x : arr){
            AreaEntity areaEntity = new AreaEntity();
            areaEntity.setValue(Integer.valueOf(x));
            areaEntity.setBuilding(mapBuilding);
            listAreaEntity.add(areaEntity);
        }
        return listAreaEntity;
    }
}
