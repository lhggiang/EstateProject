package com.javaweb.enums;

import java.util.*;

public enum typeCode {
    NOI_THAT("Nội thất"),
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn");
    private final String typeCodeName;
    typeCode (String typeCodeName){
        this.typeCodeName = typeCodeName;
    }
    public String getTypeCodeName(){
        return typeCodeName;
    }
    public static Map<String,String> typeCode(){
        Map<String,String> listTypeCode = new TreeMap<>();
        for(typeCode item : typeCode.values()){
            listTypeCode.put(item.toString(),item.getTypeCodeName());
        }
        return listTypeCode;
    }
    public static List<String> listTypeCode(){
        List<String> list = new ArrayList<>();
        for(typeCode item : typeCode.values()){
            list.add(item.toString());
        }
        return list;
    }
}
