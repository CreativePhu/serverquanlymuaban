package dao.implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.QuyenInf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Quyen;

public class QuyenImp extends UnicastRemoteObject implements QuyenInf{
	
	private EntityManager entityManager;

	public QuyenImp(EntityManager entityManager) throws RemoteException {
        this.entityManager = entityManager;
	}

	@Override
	public void taoQuyen(Quyen quyen) throws RemoteException {
		EntityTransaction session = entityManager.getTransaction();
		try {
			session.begin();
			entityManager.persist(quyen);
			entityManager.flush();
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}
	}

	@Override
	public List<Quyen> timQuyenBangTen(String tenQuyen) throws RemoteException {
		Query query = entityManager.createQuery("select q from Quyen q where q.tenQuyen =: tenquyen", Quyen.class);
		query.setParameter("tenquyen", tenQuyen);
		return query.getResultList();
	}

	@Override
	public Quyen timQuyenBangId(int id) throws RemoteException {
		return entityManager.find(Quyen.class, id);
	}

	@Override
	public List<Quyen> layDanhSachQuyen() throws RemoteException {
		Query query = entityManager.createQuery("select q from Quyen q", Quyen.class);
		return query.getResultList();
	}

	@Override
	public void capNhatQuyen(Quyen quyen) throws RemoteException {
		EntityTransaction session = entityManager.getTransaction();
		try {
			session.begin();
			entityManager.merge(quyen);
			entityManager.flush();
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}
	}

	@Override
	public void xoaQuyen(int id) throws RemoteException {
		EntityTransaction session = entityManager.getTransaction();
		try {
			session.begin();
			Quyen quyen = entityManager.find(Quyen.class, id);
			entityManager.remove(quyen);
			entityManager.flush();
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}
	}

}
