package dao;

import java.util.List;

import model.NhanVien;

public interface NhanVienInf {
	public void taoNhanVien(NhanVien nhanVien);

	public List<NhanVien> layDanhSachNhanVien();
}
