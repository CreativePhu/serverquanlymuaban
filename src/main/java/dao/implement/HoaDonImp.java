package dao.implement;

import dao.HoaDonInf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import model.ChiTietHoaDon;
import model.HoaDon;
import model.NhanVien;
import model.SanPham;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HoaDonImp extends UnicastRemoteObject implements HoaDonInf {

    private EntityManager entityManager;

    public HoaDonImp(EntityManager entityManager) throws RemoteException {
        this.entityManager = entityManager;
    }

    @Override
    public void taoHoaDon(List<ChiTietHoaDon> dsChiTietHoaDon, long idNhanVien) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            HoaDon hoaDon = new HoaDon(new Date(), entityManager.find(NhanVien.class, idNhanVien));
            for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
                SanPham sanPham = entityManager.find(SanPham.class, chiTietHoaDon.getSanPham().getIdSanPham());
                chiTietHoaDon.setSanPham(sanPham);
                chiTietHoaDon.setHoaDon(hoaDon);
                hoaDon.themChiTietHoaDon(chiTietHoaDon);
            }
            hoaDon.tinhtongTien();
            entityManager.persist(hoaDon);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<HoaDon> layDanhSachHoaDon() throws RemoteException {
        Query query = entityManager.createQuery("SELECT hd FROM HoaDon hd");
        return query.getResultList();
    }

    @Override
    public List<HoaDon> timKiemHoaDon(String maHoaDon, String maNhanVien, Date ngayLapHoaDon) throws RemoteException {
        String query = "SELECT hd.* FROM hoa_don hd WHERE 1=1";

        if (maHoaDon != null && !maHoaDon.isEmpty()) {
            query += " AND hd.ma_hoa_don = :maHoaDon";
        }
        if (maNhanVien != null && !maNhanVien.isEmpty()) {
            query += " AND hd.id_nhan_vien = :maNhanVien";
        }
        if (ngayLapHoaDon != null) {
            query += " AND DAY(hd.ngay_lap) = :day AND MONTH(hd.ngay_lap) = :month AND YEAR(hd.ngay_lap) = :year";
        }

        Query q = entityManager.createNativeQuery(query, HoaDon.class);

        if (maHoaDon != null && !maHoaDon.isEmpty()) {
            q.setParameter("maHoaDon", Long.parseLong(maHoaDon));
        }
        if (maNhanVien != null && !maNhanVien.isEmpty()) {
            q.setParameter("maNhanVien", Long.parseLong(maNhanVien));
        }
        if (ngayLapHoaDon != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(ngayLapHoaDon);
            q.setParameter("day", cal.get(Calendar.DAY_OF_MONTH));
            q.setParameter("month", cal.get(Calendar.MONTH) + 1);
            q.setParameter("year", cal.get(Calendar.YEAR));
        }

        return q.getResultList();
    }

    @Override
    public void capNhatNgayLapHoaDon(String maHoaDon, Date ngayLapMoi) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            HoaDon hoaDon = entityManager.find(HoaDon.class, Long.parseLong(maHoaDon));
            hoaDon.setNgayLap(ngayLapMoi);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void xoaHoaDon(String maHoaDon) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            HoaDon hoaDon = entityManager.find(HoaDon.class, Long.parseLong(maHoaDon));
            entityManager.remove(hoaDon);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public HoaDon layHoaDonTheoMa(String maHoaDon) throws RemoteException {
        return entityManager.find(HoaDon.class, Long.parseLong(maHoaDon));
    }


}
