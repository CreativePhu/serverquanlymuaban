package dao.implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.LoaiSanPhamInf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import model.LoaiSanPham;

public class LoaiSanPhamImp extends UnicastRemoteObject implements LoaiSanPhamInf{
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	public LoaiSanPhamImp(EntityManager entityManager1) throws RemoteException {
		entityManager = entityManager1;
	}

	@Override
	public void taoLoaiSanPham(LoaiSanPham loaiSanPham) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(loaiSanPham);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
		
	}

	@Override
	public List<LoaiSanPham> layDanhSachLoaiSanPham() throws RemoteException {
		return entityManager.createQuery("SELECT l FROM LoaiSanPham l", LoaiSanPham.class).getResultList();
	}

	@Override
	public List<LoaiSanPham> timLoaiSanPhamBangTen(String tenLoai) throws RemoteException {
		Query query = entityManager.createQuery("SELECT l FROM LoaiSanPham l WHERE l.tenLoai = :tenLoai", LoaiSanPham.class);
		query.setParameter("tenLoai", tenLoai);
		return query.getResultList();
	}
	
	
}
