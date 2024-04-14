package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "san_pham")
public class SanPham implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_san_pham")
	private long idSanPham;

	@Column(name = "ten_san_pham", nullable = false, length = 200)
	private String tenSanPham;

	@Column(name = "gia_san_pham", nullable = false)
	private float giaSanPham;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "loaiSanPham_sanPham", joinColumns = @JoinColumn(name = "id_san_pham"), inverseJoinColumns = @JoinColumn(name = "ma_loai_sp"))
	private Set<LoaiSanPham> danhSachLoaiSanPham;
	
	@OneToMany(mappedBy = "sanPham" ,cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Set<ChiTietHoaDon> danhSachChiTietHoaDon;

	public SanPham(String tenSanPham, float giaSanPham) {
		this.tenSanPham = tenSanPham;
		this.giaSanPham = giaSanPham;
	}


	public void addLoaiSanPham(LoaiSanPham loaiSanPham) {
		if (danhSachLoaiSanPham == null) {
			danhSachLoaiSanPham = new HashSet<LoaiSanPham>();
		}
		danhSachLoaiSanPham.add(loaiSanPham);
	}

}
