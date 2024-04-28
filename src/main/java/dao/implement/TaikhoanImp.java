package dao.implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.TaiKhoanInf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import model.Quyen;
import model.TaiKhoan;
import util.ConnectDB;

public class TaikhoanImp extends UnicastRemoteObject implements TaiKhoanInf{
	
	private EntityManager entityManager;

	public TaikhoanImp(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}

	@Override
	public List<TaiKhoan> layDanhSachTaiKhoan() throws RemoteException {
		Query query = entityManager.createQuery("select tk from TaiKhoan tk");
		List<TaiKhoan> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void capNhatTaiKhoan(TaiKhoan taiKhoan, Long maQuyenHan) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			Quyen quyen = entityManager.find(Quyen.class, maQuyenHan);
			taiKhoan.setQuyen(quyen);
			entityManager.merge(taiKhoan);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public List<TaiKhoan> timKiemTaiKhoan(String tenTaiKhoan, String maQuyen, String tenTrangThai) throws RemoteException {
		String query = "select tk from TaiKhoan tk where 1=1";

		if(tenTaiKhoan != null && !tenTaiKhoan.isEmpty()) {
			query += " and tk.tenTaiKhoan like :tenTaiKhoan";
		}

		if(maQuyen != null && !maQuyen.isEmpty()) {
			query += " and tk.quyen.idQuyen = :maQuyen";
		}

		if (tenTrangThai != null && !tenTrangThai.isEmpty()) {
			query += " and tk.trangThai = :trangThai";
		}

		Query q = entityManager.createQuery(query);

		if(tenTaiKhoan != null && !tenTaiKhoan.isEmpty()) {
			q.setParameter("tenTaiKhoan", "%" + tenTaiKhoan + "%");
		}

		if(maQuyen != null && !maQuyen.isEmpty()) {
			q.setParameter("maQuyen", Long.parseLong(maQuyen));
		}

		if (tenTrangThai != null && !tenTrangThai.isEmpty()) {
			q.setParameter("trangThai", tenTrangThai.equals("Kích hoạt") ? true : false);
		}

		List<TaiKhoan> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public TaiKhoan xacThucTaiKhoan(String tenTaiKhoan, String matKhau) throws RemoteException {
		Query query = entityManager.createQuery("select tk from TaiKhoan tk where tk.tenTaiKhoan = :tenTaiKhoan and tk.matKhau = :matKhau");
		query.setParameter("tenTaiKhoan", tenTaiKhoan);
		query.setParameter("matKhau", matKhau);
		List<TaiKhoan> resultList = query.getResultList();

		if(resultList.size() > 0) {
			return resultList.get(0);
		}

		return null;
	}

}
