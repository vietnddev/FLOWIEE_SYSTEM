package com.flowiee.pms.utilities.enums;

import lombok.Getter;

@Getter
public enum TicketImportStatus {
    DRAFT("Lưu nháp"),
    COMPLETED("Hoàn thành"),
    CANCEL("Hủy");

    private final String label;

    TicketImportStatus(String label) {
        this.label = label;
    }
}