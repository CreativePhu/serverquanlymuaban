package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.Quyen;

public interface QuyenInf extends Remote {
	public void taoQuyen(Quyen quyen) throws RemoteException;
	
	public List<Quyen> timQuyenBangTen(String tenQuyen) throws RemoteException;
	
	public Quyen timQuyenBangId(int id) throws RemoteException;

	public List<Quyen> layDanhSachQuyen() throws RemoteException;
}
