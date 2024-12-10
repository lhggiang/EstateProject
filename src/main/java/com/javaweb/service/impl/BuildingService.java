package com.javaweb.service.impl;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuildingSearchConverter;
import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.AreaEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.typeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingAssignmentRequest;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.TypeCodeResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.tomcat.util.codec.binary.Base64;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class BuildingService implements IBuildingService {
    private final UploadFileUtils uploadFileUtils;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingSearchConverter buildingSearchConverter;
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentAreaConverter rentAreaConverter;
    @Autowired
    public BuildingService(UploadFileUtils uploadFileUtils) {
        this.uploadFileUtils = uploadFileUtils;
    }
    //tìm tòa nhà
    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.findBuildings(buildingSearchBuilder);
        List<BuildingSearchResponse> result = new ArrayList<>();
        for(BuildingEntity item : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingSearchConverter.toBuildingResponse(item);
            result.add(buildingSearchResponse);
        }
        return result;
    }
    @Override
    public List<BuildingSearchResponse> getBuildings(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.findBuildings(buildingSearchBuilder);
        Page<BuildingEntity> buildings = new PageImpl<>(buildingEntities);
        List<BuildingEntity> newsEntities = buildings.getContent();
        List<BuildingSearchResponse> results = new ArrayList<>();
        for (BuildingEntity buildingEntity : newsEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingSearchConverter.toBuildingResponse(buildingEntity);
            results.add(buildingSearchResponse);
        }
        return results;
    }

    @Override
    @Transactional
    public void saveOrUpdate(BuildingDTO buildingDTO) {
        BuildingEntity mapBuilding = buildingConverter.convertToBuildingEntity(buildingDTO);
        if (buildingDTO.getId() != null) { // update
            mapBuilding.setImage(buildingDTO.getImage());
        }
        saveThumbnail(buildingDTO, mapBuilding);
        if(mapBuilding.getId()!=null) {
            BuildingEntity building = buildingRepository.findById(buildingDTO.getId()).get();
            List<Integer> list = building.getAreas().stream().map(item->item.getId()).collect(Collectors.toList());
            if (list != null) {
                rentAreaRepository.deleteByIdIn(list);
            }
        }
        //cascade
        if(!buildingDTO.getRentArea().isEmpty()){
            List<AreaEntity> listAreaEntity = rentAreaConverter.convertToRentAreaEntity(buildingDTO,mapBuilding);
            mapBuilding.setAreas(listAreaEntity);
        }
        buildingRepository.save(mapBuilding);
    }

    @Override
    public BuildingDTO findByID(Long id) {
        BuildingEntity model = buildingRepository.findById(id).get();
        BuildingDTO dto = modelMapper.map(model,BuildingDTO.class);
        List<AreaEntity> list = model.getAreas();
        String value="";
        for(AreaEntity it : list){
            value+=it.getValue() + ",";
        }
        //loại bỏ dấu , cuối
        if(value!=""){
            String newStr = value.substring(0, value.length() - 1);
            dto.setRentArea(newStr);
        }
        return dto;
    }

    //lưu giao tòa nhà
    @Override
    public void save(BuildingAssignmentRequest request) {
        Long buildingId = request.getBuildingId();
        List<Long> staffIds = request.getStaffs();
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        //xóa liên kết
        building.getAssignmentbuildings().clear();
        List<UserEntity> staffList = userRepository.findAllById(staffIds);
        //tạo liên kết mới
        building.getAssignmentbuildings().addAll(staffList);
        buildingRepository.save(building);
    }
    //xóa tòa nhà
    @Override
    @Transactional
    public void deleteBuilding(Long id) {
        buildingRepository.deleteBuilding(buildingRepository.findById(id).get());
    }
    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }
    //load TypeCode
    public ResponseDTO listTypeCode(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        Map<String,String> map = typeCode.typeCode();
        ResponseDTO results = new ResponseDTO();
        List<TypeCodeResponseDTO> list = new ArrayList<>();
        for(String key : map.keySet()){
            TypeCodeResponseDTO typeCodeDTO = new TypeCodeResponseDTO();
            typeCodeDTO.setTypeCode(key);
            typeCodeDTO.setFullName(map.get(key));
            if(building.getTypeCode().contains(key)){
                typeCodeDTO.setChecked("checked");
            } else {
                typeCodeDTO.setChecked("");
            }
            list.add(typeCodeDTO);
        }
        results.setMessage("SUCCESS");
        results.setData(list);
        return results;
    }
}
