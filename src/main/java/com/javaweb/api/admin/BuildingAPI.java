package com.javaweb.api.admin;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingAssignmentRequest;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/api/building")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private IUserService userService;
    //thêm/cập nhật tòa nhà
    @PostMapping
    public BuildingDTO createAndUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        buildingService.saveOrUpdate(buildingDTO);
        return buildingDTO;
    }
    //xóa tòa nhà
    @DeleteMapping
    public void deleteBuilding(@RequestBody Map<String,String> mp){
        //nhận về string các id tòa nhà cần xóa
        for (Map.Entry<String, String> entry : mp.entrySet()) {
            String value = entry.getValue();
            String[] listID = value.split(",");
            for(String item : listID){
                buildingService.deleteBuilding(Long.parseLong(item));
            }
        }
    }
    //load staffs
    @GetMapping("/{buildingId}/staffs")
    public ResponseDTO loadStaff (@PathVariable("buildingId") Long id){
        ResponseDTO result = userService.listStaff(id);
        return result;
    }
    //load typeCode
    @GetMapping("/{buildingId}/typeCodes")
    public ResponseDTO loadTypeCode (@PathVariable("buildingId") Long id){
        ResponseDTO result = buildingService.listTypeCode(id);
        return result;
    }
    //giao tòa nhà
    @PostMapping(value="/{id}")
    public void assignBuilding(@RequestBody BuildingAssignmentRequest request ){
        buildingService.save(request);
    }
}
