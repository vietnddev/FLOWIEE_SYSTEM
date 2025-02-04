package com.flowiee.pms.service;

import com.flowiee.pms.model.EximModel;
import com.flowiee.pms.utilities.enums.TemplateExport;

public interface ExportService {
    EximModel exportToExcel(TemplateExport templateExport, Object pCondition, boolean templateOnly);
}