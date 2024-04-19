package dao.implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.SanPhamInf;
import jakarta.persistence.EntityManager;
import model.SanPham;

public class SanPhamImp extends UnicastRemoteObject implements SanPhamInf {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public SanPhamImp(EntityManager entityManager1) throws RemoteException {
		entityManager = entityManager1;
	}

	@Override
	public void taoSanPham(SanPham sanPham) {
		try {
			entityManager.getTransaction().begin();
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
}
