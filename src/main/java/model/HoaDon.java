package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
@Table(name = "hoa_don")
public class HoaDon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hoa_don")
    private long maHoaDon;

    @Column(name = "ngay_lap", nullable = false)
    private Date ngayLap;

    @Column(name = "tong_tien", nullable = false)
    private float tongTien;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nhan_vien", nullable = false)
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ChiTietHoaDon> danhSachChiTietHoaDon;


    public HoaDon(Date ngayLap, float tongTien) {
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }


    public void themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        if (danhSachChiTietHoaDon == null) {
            danhSachChiTietHoaDon = new HashSet<ChiTietHoaDon>();
        }
        danhSachChiTietHoaDon.add(chiTietHoaDon);
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "tongTien=" + tongTien +
                ", ngayLap=" + ngayLap +
                ", maHoaDon=" + maHoaDon +
                '}';
    }
}
