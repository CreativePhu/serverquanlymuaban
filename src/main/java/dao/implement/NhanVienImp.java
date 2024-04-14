package dao.implement;

import java.util.List;
import dao.NhanVienInf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.NhanVien;
import util.HibernateUtil;

public class NhanVienImp implements NhanVienInf{
	
	private EntityManager entityManager = HibernateUtil.getEntityManager();

	@Override
	public void taoNhanVien(NhanVien nhanVien) {
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

}
