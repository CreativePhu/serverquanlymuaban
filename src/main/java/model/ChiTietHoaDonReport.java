package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChiTietHoaDonReport {
    private String tenSanPham;
    private int soLuong;
    private float giaSanPham;
    private String tongTien;
}
