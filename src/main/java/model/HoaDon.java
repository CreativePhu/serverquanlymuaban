package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_nhan_vien", nullable = false)
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL)
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

}
