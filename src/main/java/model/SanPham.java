package model;

import java.io.Serializable;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_loai_san_pham")
    private LoaiSanPham loaiSanPham;


    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "sanPham")
    private List<ChiTietHoaDon> chiTietHoaDon;

    public SanPham(String tenSanPham, float giaSanPham) {
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
    }

    public SanPham(long idSanPham, String tenSanPham, float giaSanPham) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SanPham sanPham = (SanPham) o;
        return idSanPham == sanPham.idSanPham;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idSanPham);
    }

    @Override
    public String toString() {
        return idSanPham+"";
    }
}
