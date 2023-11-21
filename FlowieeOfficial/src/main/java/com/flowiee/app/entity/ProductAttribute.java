package com.flowiee.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flowiee.app.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pro_san_pham_thuoc_tinh")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductAttribute extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnoreProperties("listThuocTinh")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bien_the_id", nullable = false)
    private ProductVariant productVariant;

    @Column(name = "ten_thuoc_tinh", nullable = false)
    private String tenThuocTinh;

    @Column(name = "gia_tri_thuoc_tinh", length = 500)
    private String giaTriThuocTinh;

    @Column(name = "sort", nullable = false)
    private int sort;

    @Column(name = "trang_thai", nullable = false)
    private boolean trangThai;

    @OneToMany(mappedBy = "productAttribute", fetch = FetchType.LAZY)
    private List<ProductHistory> listProductHistory;

    @Override
    public String toString() {
        return "ThuocTinhSanPham{" +
                "bienTheSanPham=" + productVariant +
                ", tenThuocTinh='" + tenThuocTinh + '\'' +
                ", giaTriThuocTinh='" + giaTriThuocTinh + '\'' +
                ", sort=" + sort +
                ", trangThai=" + trangThai +
                '}';
    }
}