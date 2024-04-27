package dao;

import model.ChiTietHoaDon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface HoaDonInf extends Remote {
    public void taoHoaDon(List<ChiTietHoaDon> dsChiTietHoaDon, long idNhanVien) throws RemoteException;
}
