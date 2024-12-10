package com.javaweb.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionDTO extends AbstractDTO {
    //private Long id;
    private Long customerId;
    private String code;
    @JsonProperty(value="transactionDetail")
    private String note;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
