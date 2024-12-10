package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum transactionType {
    CSKH("Chăm Sóc Khách Hàng"),
    DDX("Dẫn Đi Xem");
    private final String name;

    transactionType(String name) {
        this.name = name;
    }

    public static Map<String, String> transactionType() {
        Map<String, String> typeCodes = new TreeMap<>();
        for (transactionType it : transactionType.values()) {
            typeCodes.put(it.toString(), it.name);
        }
        return typeCodes;
    }
}