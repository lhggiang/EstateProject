package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaweb.service.ITransactionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService{
    @Autowired
    private TransactionConverter transactionConverter;
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public List<TransactionDTO> findByCode(String code,Long id) {
        List<TransactionEntity> transactionEntities = transactionRepository.findByCodeAndCustomerId(code,id);
        return transactionEntities.stream().map(item->transactionConverter.convertToTransactionDTO(item)).collect(Collectors.toList());
    }

    @Override
    public void saveOrUpdate(TransactionDTO transactionDTO) {
        TransactionEntity entity = transactionConverter.convertToTransactionEntity(transactionDTO);
        transactionRepository.save(entity);
    }

    @Override
    public TransactionDTO loadTransactionDetail(Long id) {
        TransactionEntity entity = transactionRepository.findById(id).get();
        return transactionConverter.convertToTransactionDTO(entity);
    }
}
