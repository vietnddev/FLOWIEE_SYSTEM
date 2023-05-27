package com.flowiee.app.products.services;

import com.flowiee.app.products.entity.SanPham;

import java.util.List;

public interface SanPhamService {

    List<SanPham> getAllProducts();

    SanPham findById(int productID);

    void insertProduct(SanPham sanPham);

    void update(SanPham sanPham, int id);

    void deleteProduct(int productID);
}