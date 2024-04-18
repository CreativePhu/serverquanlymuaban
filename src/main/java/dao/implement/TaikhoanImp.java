package dao.implement;

import java.util.List;

import dao.TaiKhoanInf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import model.TaiKhoan;
import util.HibernateUtil;

public class TaikhoanImp implements TaiKhoanInf{
	
	private EntityManager entityManager = HibernateUtil.getEntityManager();

	@Override
	public List<TaiKhoan> layDanhSachTaiKhoan() {
		Query query = entityManager.createQuery("select tk from TaiKhoan tk");
		List<TaiKhoan> resultList = query.getResultList();
		return resultList;
	}

}
