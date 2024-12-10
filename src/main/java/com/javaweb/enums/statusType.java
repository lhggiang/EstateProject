package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum statusType {
    DA_XU_LY("Đã xử lý"),
    DANG_XU_LY("Đang xử lý"),
    CHUA_XU_LY("Chưa xử lý");

    private final String statusName;
    statusType(String status) {
        this.statusName = status;
    }

    public String getStatusName(){
        return statusName;
    }
    public static Map<String, String> statusType() {
        Map<String, String> statusList = new TreeMap<>();
        for (statusType it : statusType.values()) {
            statusList.put(it.toString(), it.statusName);
        }
        return statusList;
    }
}
