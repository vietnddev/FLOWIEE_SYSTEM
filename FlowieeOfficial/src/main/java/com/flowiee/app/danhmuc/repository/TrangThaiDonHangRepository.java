package com.flowiee.app.danhmuc.repository;

import com.flowiee.app.sanpham.model.TrangThaiDonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrangThaiDonHangRepository extends JpaRepository<TrangThaiDonHang, Integer> {
}