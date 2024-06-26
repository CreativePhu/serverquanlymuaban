package dao.implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.LoaiSanPhamInf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.LoaiSanPham;
import org.hibernate.Session;

public class LoaiSanPhamImp extends UnicastRemoteObject implements LoaiSanPhamInf{
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	public LoaiSanPhamImp(EntityManager entityManager1) throws RemoteException {
		entityManager = entityManager1;
	}

	@Override
	public void taoLoaiSanPham(LoaiSanPham loaiSanPham) throws RemoteException {
		EntityTransaction session = entityManager.getTransaction();
		try {
			session.begin();
			entityManager.persist(loaiSanPham);
			entityManager.flush();
			session.commit();
		} catch (Exception e) {
			session.rollback();
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

	public LoaiSanPham timLoaiSanPhamBangId(long id) throws RemoteException {
		return entityManager.find(LoaiSanPham.class, id);
	}

	@Override
	public void xoaLoaiSanPham(long id) throws RemoteException {
		EntityTransaction session = entityManager.getTransaction();
		try {
			session.begin();
			LoaiSanPham loaiSanPham = entityManager.find(LoaiSanPham.class, id);
			if(loaiSanPham.getSanPham() != null && !loaiSanPham.getSanPham().isEmpty()){
				throw new RemoteException("Loại sản phẩm này đang được sử dụng");
			}
			entityManager.remove(loaiSanPham);
			entityManager.flush();
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		}
	}

	@Override
	public void capNhatLoaiSanPham(LoaiSanPham loaiSanPham) throws RemoteException {
		EntityTransaction session = entityManager.getTransaction();
		try {
			session.begin();
			entityManager.merge(loaiSanPham);
			entityManager.flush();
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}
	}

}
