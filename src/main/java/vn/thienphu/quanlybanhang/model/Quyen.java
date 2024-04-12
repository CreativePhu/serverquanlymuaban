package vn.thienphu.quanlybanhang.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quyen")
public class Quyen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_quyen")
	private long idQuyen;
	@Column(name = "ten_quyen")
	private String tenQuyen;
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "quyen_taiKhoan",
	joinColumns = @JoinColumn(name = "id_quyen"),
	inverseJoinColumns = @JoinColumn(name = "ten_tai_khoan")
	)
	private List<TaiKhoan> danhSachTaiKhoan;

	public Quyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}

}
