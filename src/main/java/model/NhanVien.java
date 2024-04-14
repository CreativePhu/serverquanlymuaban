package model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhan_vien")
public class NhanVien implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_nhan_vien")
	private long idNhanVien;

	@Column(name = "ten_nhan_vien", nullable = false, length = 200)
	private String tenNhanVien;

	@Column(name = "so_dien_thoai", nullable = false, length = 20, unique = true)
	private String soDienThoai;

	@Column(name = "gmail", nullable = true, length = 100, unique = true)
	private String gmail;

	@Column(name = "dia_chi", nullable = true, length = 200)
	private String diaChi;

	@Column(name = "gioi_tinh", nullable = false)
	private boolean gioiTinh;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ten_tai_khoan")
	private TaiKhoan taiKhoan;

	@OneToMany(mappedBy = "nhanVien", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<HoaDon> danhSachHoaDon;


	public NhanVien(String tenNhanVien, String soDienThoai, String gmail) {
		this.tenNhanVien = tenNhanVien;
		this.soDienThoai = soDienThoai;
		this.gmail = gmail;
	}


}
