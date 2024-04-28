package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.TaiKhoan;

public interface TaiKhoanInf extends Remote {
	public List<TaiKhoan> layDanhSachTaiKhoan() throws RemoteException;
	public void capNhatTaiKhoan(TaiKhoan taiKhoan, Long maQuyenHan) throws RemoteException;
	public List<TaiKhoan> timKiemTaiKhoan(String tenTaiKhoan, String maQuyen, String trangThai) throws RemoteException;
	public TaiKhoan xacThucTaiKhoan(String tenTaiKhoan, String matKhau) throws RemoteException;
}
