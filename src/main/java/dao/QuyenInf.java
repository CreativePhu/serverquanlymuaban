package dao;

import java.util.List;

import model.Quyen;

public interface QuyenInf {
	public void taoQuyen(Quyen quyen);
	
	public List<Quyen> timQuyenBangTen(String tenQuyen);
	
	public Quyen timQuyenBangId(int id);

	public List<Quyen> layDanhSachQuyen();
}
