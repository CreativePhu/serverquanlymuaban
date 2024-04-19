package run;

import java.rmi.RemoteException;
import java.util.List;

import dao.implement.NhanVienImp;
import dao.implement.QuyenImp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.NhanVien;
import model.Quyen;

public class RunDB {
	public static void main(String[] args) throws RemoteException {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA_ORM_QLMuaBan");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		QuyenImp quyenImp = new QuyenImp(entityManager);
		NhanVienImp nhanVienImp = new NhanVienImp(entityManager);
		
		Quyen quyen = new Quyen("Staff");
		Quyen quyen1 = new Quyen("Manager");
		
		quyenImp.taoQuyen(quyen);
		quyenImp.taoQuyen(quyen1);
		
//		Quyen quyen2 = quyenImp.timQuyenBangTen("Staff").get(0);
//		System.out.println(quyen2);
		
		NhanVien nhanVien = new NhanVien("Ngô Thiên Phú", "0348191482", true);
//		nhanVien.taoTaiKhoanTuDong();
		nhanVienImp.taoNhanVien(nhanVien);

		List<Quyen> dsQuyen = quyenImp.layDanhSachQuyen();
		for (Quyen q : dsQuyen) {
			System.out.println(q);
		}
	}
}
