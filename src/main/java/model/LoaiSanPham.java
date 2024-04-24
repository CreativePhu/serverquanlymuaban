package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Entity
@Table(name = "loai_san_pham")
public class LoaiSanPham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_loai")
    private long maLoai;

    @Column(name = "ten_loai", nullable = false, length = 150)
    private String tenLoai;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "loaiSanPham")
    private List<SanPham> sanPham;

    public LoaiSanPham(String tenLoai) {
        this.tenLoai = tenLoai;
    }

	@Override
	public String toString() {
		return tenLoai;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoaiSanPham that = (LoaiSanPham) o;
        return maLoai == that.maLoai && Objects.equals(tenLoai, that.tenLoai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maLoai, tenLoai);
    }
}
