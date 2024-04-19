package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.TaiKhoan;

public interface TaiKhoanInf extends Remote {
	public List<TaiKhoan> layDanhSachTaiKhoan() throws RemoteException;
}
