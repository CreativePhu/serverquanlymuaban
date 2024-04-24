package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.SanPham;

public interface SanPhamInf extends Remote{
	public void taoSanPham(SanPham sanPham, Long maLoaiSanPham) throws RemoteException;
	public List<SanPham> layDanhSachSanPham() throws RemoteException;
	public List<SanPham> timKiemSanPham(String maSanPham ,String tenSanPham, String loaiSanPham) throws RemoteException;
	public void capNhatSanPham(SanPham sanPham, Long maLoaiSanPham) throws RemoteException;
	public void xoaSanPham(Long idSanPham) throws RemoteException;
}
