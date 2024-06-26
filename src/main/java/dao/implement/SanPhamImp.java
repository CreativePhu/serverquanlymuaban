package dao.implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.SanPhamInf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import model.LoaiSanPham;
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
			LoaiSanPham loaiSanPham = entityManager.find(LoaiSanPham.class, maLoaiSanPham);
			SanPham sanPham1 = entityManager.find(SanPham.class, sanPham.getIdSanPham());
			sanPham1.setTenSanPham(sanPham.getTenSanPham());
			sanPham1.setGiaSanPham(sanPham.getGiaSanPham());
			sanPham1.setLoaiSanPham(loaiSanPham);
			entityManager.merge(sanPham1);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void xoaSanPham(Long idSanPham) throws RemoteException {
		Query query = entityManager.createQuery("DELETE FROM SanPham sp WHERE sp.idSanPham = :idSanPham");
		try {
			entityManager.getTransaction().begin();
			query.setParameter("idSanPham", idSanPham);
			query.executeUpdate();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RemoteException("San pham dang duoc su dung trong hoa don. Khong the xoa!");
		}
	}

}
