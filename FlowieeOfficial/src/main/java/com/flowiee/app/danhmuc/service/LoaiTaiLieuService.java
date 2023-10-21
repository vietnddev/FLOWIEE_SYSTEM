package com.flowiee.app.danhmuc.service;

import com.flowiee.app.base.BaseService;
import com.flowiee.app.danhmuc.entity.LoaiTaiLieu;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LoaiTaiLieuService extends BaseService<LoaiTaiLieu> {
    List<LoaiTaiLieu> findAllWhereStatusTrue();

    LoaiTaiLieu findByTen(String ten);

    LoaiTaiLieu findDocTypeDefault();

    String importData(MultipartFile fileImport);

    byte[] exportTemplate();

    byte[] exportData();
}