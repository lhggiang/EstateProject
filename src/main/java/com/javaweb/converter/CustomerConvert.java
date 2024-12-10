package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.statusType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.CustomerSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerConvert {
    @Autowired
    private ModelMapper modelMapper;
    public CustomerSearchResponse convertToCustomerSearchResponse(CustomerEntity customerEntity){
        return modelMapper.map(customerEntity,CustomerSearchResponse.class);
    }
    public CustomerDTO convertToCustomerDTO(CustomerEntity customerEntity){
        return modelMapper.map(customerEntity,CustomerDTO.class);
    }
    public CustomerEntity convertToCustomerEntity(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO,CustomerEntity.class);
    }
}
