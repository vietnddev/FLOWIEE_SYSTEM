package com.flowiee.app.entity;

import com.flowiee.app.base.BaseEntity;
import com.flowiee.app.category.Category;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pro_don_hang_thanh_toan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderPay extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "don_hang_id", nullable = false)
    private Order order;

    @Column(name = "ma_phieu", nullable = false)
    private String maPhieu;

    @Column(name = "thoi_gian_thanh_toan", nullable = false)
    private Date thoiGianThanhToan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hinh_thuc_thanh_toan", nullable = false)
    private Category hinhThucThanhToan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cashier", nullable = false)
    private Account thuNgan;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "trang_thai_thanh_toan", nullable = false)
    private boolean trangThaiThanhToan;

    @Override
    public String toString() {
        return "DonHangThanhToan{" +
                "donHang=" + order +
                ", maPhieu='" + maPhieu + '\'' +
                ", thoiGianThanhToan=" + thoiGianThanhToan +
                ", hinhThucThanhToan=" + hinhThucThanhToan +
                ", thuNgan=" + thuNgan +
                ", ghiChu='" + ghiChu + '\'' +
                ", trangThaiThanhToan=" + trangThaiThanhToan +
                '}';
    }
}