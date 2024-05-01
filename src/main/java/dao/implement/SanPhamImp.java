package dao.implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.SanPhamInf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.SanPham;

public class SanPhamImp extends UnicastRemoteObject implements SanPhamInf {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public SanPhamImp(EntityManager entityManager1) throws RemoteException {
		entityManager = entityManager1;
	}


	@Override
	public void taoSanPham(SanPham sanPham, Long maLoaiSanPham) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			sanPham.setLoaiSanPham(entityManager.find(model.LoaiSanPham.class, maLoaiSanPham));
			entityManager.persist(sanPham);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();

		}
	}

	@Override
	public List<SanPham> layDanhSachSanPham() {
		return entityManager.createQuery("SELECT sp FROM SanPham sp", SanPham.class).getResultList();
	}

	@Override
	public List<SanPham> timKiemSanPham(String maSanPham, String tenSanPham, String loaiSanPham) throws RemoteException {

		String query = "SELECT sp FROM SanPham sp WHERE 1=1";

		if (!maSanPham.isEmpty()) {
			query += " AND sp.idSanPham = :maSanPham";
		}
		if (!tenSanPham.isEmpty()) {
			query += " AND sp.tenSanPham LIKE :tenSanPham";
		}
		if (!loaiSanPham.isEmpty()) {
			query += " AND sp.loaiSanPham.tenLoai = :loaiSanPham";
		}

		Query q = entityManager.createQuery(query, SanPham.class);

		if (!maSanPham.isEmpty()) {
			q.setParameter("maSanPham", Long.parseLong(maSanPham));
		}
		if (!tenSanPham.isEmpty()) {
			q.setParameter("tenSanPham", "%" + tenSanPham + "%");
		}
		if (!loaiSanPham.isEmpty()) {
			q.setParameter("loaiSanPham", loaiSanPham);
		}

		return q.getResultList();
	}

	@Override
	public void capNhatSanPham(SanPham sanPham, Long maLoaiSanPham) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			sanPham.setLoaiSanPham(entityManager.find(model.LoaiSanPham.class, maLoaiSanPham));
			entityManager.merge(sanPham);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void xoaSanPham(Long idSanPham) throws RemoteException {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			SanPham sanPham = entityManager.find(SanPham.class, idSanPham);
			entityManager.remove(sanPham);
			transaction.commit();
		} catch (Exception e) {
            transaction.rollback();
		}
	}
}
