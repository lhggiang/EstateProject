package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.dto.UserDTO;

import java.util.List;

public interface ITransactionService {
    List<TransactionDTO> findByCode(String code, Long id);

    void saveOrUpdate(TransactionDTO transactionDTO);

    TransactionDTO loadTransactionDetail(Long id);
}
