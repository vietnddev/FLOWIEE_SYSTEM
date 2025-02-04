package com.flowiee.pms.utilities.enums;

import lombok.Getter;

@Getter
public enum ContactType {
    P("Số điện thoại"),
    E("Email"),
    A("Địa chỉ");
    private final String label;

    ContactType(String label) {
        this.label = label;
    }
}