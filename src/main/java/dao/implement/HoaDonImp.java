package dao.implement;

import dao.HoaDonInf;
import jakarta.persistence.EntityManager;
import model.ChiTietHoaDon;
import model.HoaDon;
import model.NhanVien;
import model.SanPham;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
}
