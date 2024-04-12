package vn.thienphu.quanlybanhang.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hoa_don")
public class HoaDon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_hoa_don")
	private long maHoaDon;
	
	@Column(name = "ngay_lap")
	private Date ngayLap;
	
	@Column(name = "tong_tien")
	private float tongTien;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "id_nhan_vien")
	private NhanVien nhanVien;
	
	@OneToMany(mappedBy = "hoaDon" ,cascade = CascadeType.ALL)
	private List<ChiTietHoaDon> danhSachChiTietHoaDon;


	public HoaDon(Date ngayLap, float tongTien) {
		this.ngayLap = ngayLap;
		this.tongTien = tongTien;
	}


	public void themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		if(danhSachChiTietHoaDon == null) {
			danhSachChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		}
		danhSachChiTietHoaDon.add(chiTietHoaDon);
	}
	
}
