package dao;

import model.ChiTietHoaDon;
import model.HoaDon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface HoaDonInf extends Remote {
    public void taoHoaDon(List<ChiTietHoaDon> dsChiTietHoaDon, long idNhanVien) throws RemoteException;
    public List<HoaDon> layDanhSachHoaDon() throws RemoteException;
    public List<HoaDon> timKiemHoaDon(String maHoaDon, String maNhanVien, Date ngayLapHoaDon) throws RemoteException;
}
