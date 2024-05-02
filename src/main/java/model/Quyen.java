package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
@Entity
@Table(name = "quyen")
public class Quyen implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_quyen")
	private long idQuyen;
	
	@Column(name = "ten_quyen", length = 50, nullable = false, unique = true)
	private String tenQuyen;

	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "quyen", fetch = FetchType.EAGER)
	private List<TaiKhoan> taiKhoan;

	public Quyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}

	@Override
	public String toString() {
		return tenQuyen;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Quyen quyen = (Quyen) o;
		return idQuyen == quyen.idQuyen && Objects.equals(tenQuyen, quyen.tenQuyen);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idQuyen, tenQuyen);
	}
}
