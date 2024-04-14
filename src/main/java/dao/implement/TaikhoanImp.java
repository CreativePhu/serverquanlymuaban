package dao.implement;

import java.util.List;

import dao.TaiKhoanInf;
import jakarta.persistence.EntityManager;
import model.TaiKhoan;

public class TaikhoanImp implements TaiKhoanInf{
	
	private EntityManager entityManager;
	
	public TaikhoanImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<TaiKhoan> layDanhSachTaiKhoan() {
		return null;
	}

}
