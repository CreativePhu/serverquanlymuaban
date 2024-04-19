package dao.implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import dao.NhanVienInf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.NhanVien;
import util.ConnectDB;

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


}
