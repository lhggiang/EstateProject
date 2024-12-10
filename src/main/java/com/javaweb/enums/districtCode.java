package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum districtCode {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4");
    private final String districtName;
    districtCode (String districtName){
        this.districtName = districtName;
    }
    public String getDistrictName(){
        return districtName;
    }
    public static Map<String,String> district(){
        Map<String,String> listDistrict = new TreeMap<>();
        for(districtCode item : districtCode.values()){
            listDistrict.put(item.toString(),item.getDistrictName());
        }
        return listDistrict;
    }
    public static String getDistrictName(String districtName){
        for(districtCode item : districtCode.values()){
            if(item.toString().equals(districtName))
                districtName = item.getDistrictName();
        }
        return districtName;
    }
}