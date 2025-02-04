package com.flowiee.pms.utilities.enums;

import lombok.Getter;

@Getter
public enum LedgerTranType {
    PT("Phiếu thu"),
    PC("Phiếu chi");
    private final String description;

    LedgerTranType(final String description) {
        this.description = description;
    }
}