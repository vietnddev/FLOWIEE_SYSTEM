package com.flowiee.pms.service;

import com.flowiee.pms.model.EximModel;
import com.flowiee.pms.utilities.enums.TemplateExport;
import org.springframework.web.multipart.MultipartFile;

public interface ImportService {
    EximModel importFromExcel(TemplateExport templateExport, MultipartFile multipartFile);
}