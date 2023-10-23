package com.flowiee.app.sanpham.services;

import com.flowiee.app.base.BaseService;
import com.flowiee.app.sanpham.entity.Material;

import java.util.List;

public interface MaterialService extends BaseService<Material> {
    List<Material> findByCode(String code);
}