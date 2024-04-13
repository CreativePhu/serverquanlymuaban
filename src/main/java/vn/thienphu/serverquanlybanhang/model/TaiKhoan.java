package vn.thienphu.serverquanlybanhang.model;

import java.io.Serializable;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tai_khoan")
public class TaiKhoan implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ten_tai_khoan")
	private long tenTaiKhoan;
	
	@Column(name = "mat_khau")
	private String matKhau;
	
	@Column(name = "trang_thai")
	private boolean trangThai;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "id_quyen")
	private Quyen quyen;
	
	@OneToOne(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
	private NhanVien nhanVien;

	public TaiKhoan(String matKhau, boolean trangThai) {
		this.matKhau = matKhau;
		this.trangThai = trangThai;
	}

}
