package dao.implement;

import java.util.List;

import dao.QuyenInf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Quyen;
import util.HibernateUtil;

public class QuyenImp implements QuyenInf{
	
	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	@Override
	public void taoQuyen(Quyen quyen) {
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
	public List<Quyen> timQuyenBangTen(String tenQuyen) {
		Query query = entityManager.createQuery("select q from Quyen q where q.tenQuyen =: tenquyen");
		query.setParameter("tenquyen", tenQuyen);
		return query.getResultList();
	}

	@Override
	public Quyen timQuyenBangId(int id) {
		return entityManager.find(Quyen.class, id);
	}

}
