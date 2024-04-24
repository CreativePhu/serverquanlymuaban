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

}
