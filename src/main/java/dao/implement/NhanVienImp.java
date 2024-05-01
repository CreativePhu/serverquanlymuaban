package dao.implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import dao.NhanVienInf;
import dao.QuyenInf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.HoaDon;
import model.NhanVien;
import model.Quyen;
import model.TaiKhoan;

public class NhanVienImp extends UnicastRemoteObject implements NhanVienInf{
	
	private EntityManager entityManager;

	public NhanVienImp(EntityManager entityManager) throws RemoteException {
        this.entityManager = entityManager;
	}

	@Override
	public void taoNhanVien(NhanVien nhanVien) throws RemoteException {
		EntityTransaction session = entityManager.getTransaction();
		try {
			session.begin();
			// Tạo tài khoản cho nhân viên
			QuyenInf quyenInf = new QuyenImp(entityManager);
			// Lấy quyền nhân viên
			Quyen quyen = quyenInf.timQuyenBangTen("Nhân viên").get(0);
			// Tạo tài khoản
			TaiKhoan taiKhoan = new TaiKhoan();
			taiKhoan.setQuyen(quyen);
			taiKhoan.setTenTaiKhoan(nhanVien.getSoDienThoai());
			taiKhoan.setMatKhau("123123aA");
			taiKhoan.setTrangThai(false);
			// Set nhân viên cho tài khoản
			taiKhoan.setNhanVien(nhanVien);
			// Set tài khoản cho nhân viên
			nhanVien.setTaiKhoan(taiKhoan);
			// Lưu nhân viên
			entityManager.persist(nhanVien);
			entityManager.flush();
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}
	}

	@Override
	public List<NhanVien> layDanhSachNhanVien() throws RemoteException {
		return entityManager.createQuery("select nv from NhanVien nv", NhanVien.class).getResultList();
	}

	@Override
	public List<NhanVien> timKiemNhanVien(String maNhanVien, String tenNhanVien, String soDienThoai, String gioiTinh) throws RemoteException {

		String sql = "select nv from NhanVien nv where 1=1";
		if (!maNhanVien.isEmpty()) {
			sql += " and nv.idNhanVien = :maNhanVien";
		}
		if (!tenNhanVien.isEmpty()) {
			sql += " and nv.tenNhanVien like :tenNhanVien";
		}
		if (!soDienThoai.isEmpty()) {
			sql += " and nv.soDienThoai like :soDienThoai";
		}
		if (!gioiTinh.isEmpty()) {
			sql += " and nv.gioiTinh = :gioiTinh";
		}


		Query query = entityManager.createQuery(sql.toString(), NhanVien.class);
		if (!maNhanVien.isEmpty()) {
			query.setParameter("maNhanVien", Long.parseLong(maNhanVien));
		}
		if (!tenNhanVien.isEmpty()) {
			query.setParameter("tenNhanVien", "%" + tenNhanVien + "%");
		}
		if (!soDienThoai.isEmpty()) {
			query.setParameter("soDienThoai", "%" + soDienThoai + "%");
		}
		if (!gioiTinh.isEmpty()) {
			query.setParameter("gioiTinh", gioiTinh.equals("Nam") ? true : false);
		}
		return query.getResultList();
	}

	@Override
	public void capNhatNhanVien(NhanVien nhanVien) throws RemoteException {
		EntityTransaction session = entityManager.getTransaction();
		try {
			session.begin();
			entityManager.merge(nhanVien);
			entityManager.flush();
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}
	}

	@Override
	public void xoaNhanVien(String nhanVienID) throws RemoteException {
		EntityTransaction session = entityManager.getTransaction();
		try {
			session.begin();
			NhanVien nhanVienDB = entityManager.find(NhanVien.class, Long.parseLong(nhanVienID));
			for (HoaDon hoaDon : nhanVienDB.getDanhSachHoaDon()) {
				hoaDon.setNhanVien(null);
			}
			TaiKhoan taiKhoan = nhanVienDB.getTaiKhoan();
			entityManager.remove(taiKhoan);
			entityManager.remove(nhanVienDB);
			entityManager.flush();
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}
	}

	@Override
	public NhanVien layNhanVienTheoTenTaiKhoan(String tenTaiKhoan) throws RemoteException {
		Query query = entityManager.createQuery("select nv from NhanVien nv where nv.taiKhoan.tenTaiKhoan = :tenTaiKhoan", NhanVien.class);
		query.setParameter("tenTaiKhoan", tenTaiKhoan);
		return (NhanVien) query.getSingleResult();
	}


}
