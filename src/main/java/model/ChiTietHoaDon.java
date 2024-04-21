package model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "chi_tiet_hoa_don")
public class ChiTietHoaDon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chi_tiet_hoa_don")
    private long idChiTietHoaDon;

    @Column(name = "so_luong", nullable = false)
    private int soLuong;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ma_hoa_don", nullable = false)
    private HoaDon hoaDon;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_san_pham", nullable = false)
    private SanPham sanPham;

    @Column(name = "tong_tien", nullable = false)
    private float tongTien;

    public ChiTietHoaDon(int soLuong, SanPham sanPham, float tongTien) {
        this.soLuong = soLuong;
        this.sanPham = sanPham;
        this.tongTien = tinhTongTien();
    }

    private float tinhTongTien() {
        return soLuong * sanPham.getGiaSanPham();
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" +
                "idChiTietHoaDon=" + idChiTietHoaDon +
                ", soLuong=" + soLuong +
                ", hoaDon=" + hoaDon +
                ", sanPham=" + sanPham +
                ", tongTien=" + tongTien +
                '}';
    }
}
