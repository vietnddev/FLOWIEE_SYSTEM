package com.flowiee.app.products.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "khach_hang")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class KhachHang implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "mat_khau", length = 255,nullable = false)
	private String matKhau;

	@Column(name = "ten_khach_hang", length = 100, nullable = false)
	private String tenKhachHang;

	@Column(name = "so_dien_thoai", length = 20, nullable = false)
	private String soDienThoai;

	@Column(name = "email", length = 50, nullable = true)
	private String email;

	@Column(name = "dia_chi", length = 255, nullable = false)
	private String diaChi;

	@Column(name = "trang_thai", nullable = false)
	private boolean trangThai;
}
