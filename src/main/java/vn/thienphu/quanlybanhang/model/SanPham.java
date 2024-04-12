package vn.thienphu.quanlybanhang.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "san_pham")
public class SanPham {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_san_pham")
	private long idSanPham;

	@Column(name = "ten_san_pham")
	private String tenSanPham;

	@Column(name = "gia_san_pham")
	private float giaSanPham;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "loaiSanPham_sanPham", joinColumns = @JoinColumn(name = "id_san_pham"), inverseJoinColumns = @JoinColumn(name = "ma_loai_sp"))
	private List<LoaiSanPham> danhSachLoaiSanPham;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_anh")
	private Anh anh;
	
	
	@OneToMany(mappedBy = "sanPham" ,cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<ChiTietHoaDon> danhSachChiTietHoaDon;

	public SanPham(String tenSanPham, float giaSanPham) {
		this.tenSanPham = tenSanPham;
		this.giaSanPham = giaSanPham;
	}


	public void addLoaiSanPham(LoaiSanPham loaiSanPham) {
		if (danhSachLoaiSanPham == null) {
			danhSachLoaiSanPham = new ArrayList<LoaiSanPham>();
		}
		danhSachLoaiSanPham.add(loaiSanPham);
	}

	
	public void themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		if(danhSachChiTietHoaDon == null) {
			danhSachChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		}
		
		danhSachChiTietHoaDon.add(chiTietHoaDon);
	}
}
