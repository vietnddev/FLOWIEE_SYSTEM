package com.flowiee.pms.utilities.enums;

import lombok.Getter;

@Getter
public enum CategoryStatus {
    A("Khả dụng"),
    I("Ngừng sử dụng");
    private final String label;

    CategoryStatus(String label) {
        this.label = label;
    }
}