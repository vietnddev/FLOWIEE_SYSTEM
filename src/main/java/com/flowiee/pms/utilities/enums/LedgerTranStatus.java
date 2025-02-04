package com.flowiee.pms.utilities.enums;

import lombok.Getter;

@Getter
public enum LedgerTranStatus {
    COMPLETED("Hoàn thành"),
    CANCEL("Đã hủy");
    private final String description;

    LedgerTranStatus(final String description) {
        this.description = description;
    }
}