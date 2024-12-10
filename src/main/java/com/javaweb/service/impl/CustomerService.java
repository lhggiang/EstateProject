package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.CustomerConvert;
import com.javaweb.entity.AreaEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.statusType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerAssignmentRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerConvert customerConvert;
    @Override
    public List<CustomerSearchResponse> findCustomers(CustomerSearchRequest request) {
        List<CustomerEntity> listCustomer = customerRepository.findCustomers(request);
        List<CustomerSearchResponse> result = new ArrayList<>();
        for(CustomerEntity item : listCustomer){
            CustomerSearchResponse response = customerConvert.convertToCustomerSearchResponse(item);
            //chuyển key thành value enums
            boolean isInEnum = false;
            for (statusType enumValue : statusType.values()) {
                if (enumValue.name().equals(response.getStatus())) {
                    isInEnum = true;
                    break;
                }
            }
            if (isInEnum)
            {
                String valueStatus = statusType.valueOf(response.getStatus()).getStatusName();
                response.setStatus(valueStatus);
            }
            result.add(response);
        }
        return result;
    }
    @Override
    public List<CustomerSearchResponse> getAllCustomers(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        List<CustomerEntity> customersEntities = customerRepository.findCustomers(customerSearchRequest);
        Page<CustomerEntity> customers = new PageImpl<>(customersEntities);
        List<CustomerEntity> newsEntities = customers.getContent();
        List<CustomerSearchResponse> results = new ArrayList<>();
        for (CustomerEntity customerEntity : newsEntities) {
            CustomerSearchResponse customerSearchResponse = customerConvert.convertToCustomerSearchResponse(customerEntity);
            results.add(customerSearchResponse);
        }
        return results;
    }

    @Override
    public CustomerDTO findByID(Long id) {
        CustomerEntity model = customerRepository.findById(id).get();
        CustomerDTO dto = customerConvert.convertToCustomerDTO(model);
        return dto;
    }

    @Override
    public void saveOrUpdate(CustomerDTO customerDTO) {
        CustomerEntity mapCustomer = customerConvert.convertToCustomerEntity(customerDTO);
        mapCustomer.setIsActive(1);
        if(customerDTO.getId()!=null){
            CustomerEntity entity = customerRepository.findById(customerDTO.getId()).get();
            //mapCustomer.setCreatedDate(entity.getCreatedDate());
            //mapCustomer.setCreatedBy(entity.getCreatedBy());
            mapCustomer.setAssignmentCustomers(entity.getAssignmentCustomers());
        }
        customerRepository.save(mapCustomer);
    }

    @Override
    public CustomerEntity saveAssignmentRequest(CustomerAssignmentRequest request) {
        Long customerId = request.getCustomerId();
        List<Long> staffIds = request.getStaffs();
        CustomerEntity customer = customerRepository.findById(customerId).get();
        //xóa liên kết
        customer.getAssignmentCustomers().clear();
        List<UserEntity> staffList = userRepository.findAllById(staffIds);
        //tạo liên kết mới
        customer.getAssignmentCustomers().addAll(staffList);
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        for (Long item : ids) {
            CustomerEntity customerEntity = customerRepository.findById(item).get();
            customerEntity.setIsActive(0);
            customerRepository.save(customerEntity);
        }
    }
}
