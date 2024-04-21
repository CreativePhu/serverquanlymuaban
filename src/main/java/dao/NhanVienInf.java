package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.NhanVien;

public interface NhanVienInf extends Remote {
	public void taoNhanVien(NhanVien nhanVien) throws RemoteException;
	public List<NhanVien> layDanhSachNhanVien() throws RemoteException;
	public List<NhanVien> timKiemNhanVien(String maNhanVien, String tenNhanVien,  String soDienThoai, String gioiTinh) throws RemoteException;
}
