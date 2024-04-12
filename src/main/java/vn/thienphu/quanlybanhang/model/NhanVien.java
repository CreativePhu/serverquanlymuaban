package vn.thienphu.quanlybanhang.model;

import java.util.List;
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
public class NhanVien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_nhan_vien")
	private long idNhanVien;

	@Column(name = "ten_nhan_vien")
	private String tenNhanVien;

	@Column(name = "so_dien_thoai")
	private String soDienThoai;

	@Column(name = "gmail")
	private String gmail;

	@Column(name = "dia_chi")
	private String diaChi;

	@Column(name = "gioi_tinh")
	private boolean gioiTinh;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ten_tai_khoan")
	private TaiKhoan taiKhoan;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_anh")
	private Anh anh;

	@OneToMany(mappedBy = "nhanVien", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<HoaDon> danhSachHoaDon;


	public NhanVien(String tenNhanVien, String soDienThoai, String gmail) {
		this.tenNhanVien = tenNhanVien;
		this.soDienThoai = soDienThoai;
		this.gmail = gmail;
	}


}
