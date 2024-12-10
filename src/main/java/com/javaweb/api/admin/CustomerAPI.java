package com.javaweb.api.admin;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.BuildingAssignmentRequest;
import com.javaweb.model.request.CustomerAssignmentRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.impl.CustomerService;
import com.javaweb.service.impl.TransactionService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/customer")
public class CustomerAPI {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionService transactionService;
    //thêm + cập nhật khách hàng
    @PostMapping
    public void createAndUpdateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.saveOrUpdate(customerDTO);
    }
    //load staffs
    @GetMapping("/{customerId}/staffs")
    public ResponseDTO loadStaff (@PathVariable("customerId") Long id){
        return userService.listStaffCustomers(id);
    }

    //giao khách hàng
    @PostMapping(value="/{id}")
    public CustomerEntity assignBuilding(@RequestBody CustomerAssignmentRequest request ){
        return customerService.saveAssignmentRequest(request);
    }
    //xóa khách hàng
    @DeleteMapping
    public ResponseEntity<Void> deleteCustomers(@RequestBody Long[] idList) {
        if (idList.length > 0) {
            customerService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/transaction")
    public void  addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.saveOrUpdate(transactionDTO);
    }
    //load TransactionDetail
    @GetMapping("/{transactionId}/transactionDetail")
    public TransactionDTO loadTransactionDetail (@PathVariable("transactionId") Long transactionId){
        return transactionService.loadTransactionDetail(transactionId);
    }
}
