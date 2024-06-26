package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.LoaiSanPham;

public interface LoaiSanPhamInf extends Remote {
	public void taoLoaiSanPham(LoaiSanPham loaiSanPham) throws RemoteException;
	public List<LoaiSanPham> layDanhSachLoaiSanPham() throws RemoteException;
	public List<LoaiSanPham> timLoaiSanPhamBangTen(String tenLoai) throws RemoteException;
	public LoaiSanPham timLoaiSanPhamBangId(long id) throws RemoteException;
	public void xoaLoaiSanPham(long id) throws RemoteException;
	public void capNhatLoaiSanPham(LoaiSanPham loaiSanPham) throws RemoteException;
}
