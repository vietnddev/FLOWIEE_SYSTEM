package com.flowiee.pms.utilities.enums;

import lombok.Getter;

@Getter
public enum TicketExportStatus {
    DRAFT("Lưu nháp"),
    COMPLETED("Hoàn thành"),
    CANCEL("Hủy");

    private final String label;

    TicketExportStatus(String label) {
        this.label = label;
    }
}