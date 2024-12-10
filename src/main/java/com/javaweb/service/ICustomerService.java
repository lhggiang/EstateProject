package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerAssignmentRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    List<CustomerSearchResponse> findCustomers(CustomerSearchRequest customerSearchRequest);
    List<CustomerSearchResponse> getAllCustomers(CustomerSearchRequest customerSearchRequest, Pageable pageable);
    CustomerDTO findByID(Long id);
    void saveOrUpdate(CustomerDTO customerDTO);

    CustomerEntity saveAssignmentRequest(CustomerAssignmentRequest request);
    void delete(Long[] ids);
}
