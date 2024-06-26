package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tai_khoan")

public class TaiKhoan implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tai_khoan")
	private long idTaiKhoan;
	
	@Column(name = "ten_tai_khoan", nullable = false, length = 20, unique = true)
	private String tenTaiKhoan;
	
	@Column(name = "mat_khau", nullable = false, length = 100)
	private String matKhau;
	
	@Column(name = "trang_thai", nullable = false)
	private boolean trangThai;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "id_quyen")
	private Quyen quyen;
	
	@OneToOne(mappedBy = "taiKhoan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private NhanVien nhanVien;

	public TaiKhoan(String tenTaiKhoan, String matKhau, boolean trangThai) {
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.trangThai = trangThai;
	}

	public TaiKhoan(String tenTaiKhoan, String matKhau, boolean trangThai, Quyen quyen) {
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.trangThai = trangThai;
		this.quyen = quyen;
	}

	public TaiKhoan(long idTaiKhoan, String tenTaiKhoan, String matKhau, boolean trangThai) {
		this.idTaiKhoan = idTaiKhoan;
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.trangThai = trangThai;
	}
}
