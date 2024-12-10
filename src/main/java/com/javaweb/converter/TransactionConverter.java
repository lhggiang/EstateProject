package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    @Autowired
    private ModelMapper modelMapper;
    public TransactionDTO convertToTransactionDTO(TransactionEntity transactionEntity){
        TransactionDTO dto = modelMapper.map(transactionEntity,TransactionDTO.class);
        return dto;
    }
    public TransactionEntity convertToTransactionEntity(TransactionDTO transactionDTO){
        TransactionEntity entity = modelMapper.map(transactionDTO,TransactionEntity.class);
        return entity;
    }
}
