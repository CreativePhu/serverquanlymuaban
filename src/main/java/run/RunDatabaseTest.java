package run;

import java.rmi.RemoteException;
import java.util.List;

import dao.implement.LoaiSanPhamImp;
import dao.implement.NhanVienImp;
import dao.implement.QuyenImp;
import dao.implement.SanPhamImp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.LoaiSanPham;
import model.NhanVien;
import model.Quyen;
import model.SanPham;
import model.TaiKhoan;

public class RunDatabaseTest {
	public static void main(String[] args) throws RemoteException {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA_ORM_QLMuaBan");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		QuyenImp quyenImp = new QuyenImp(entityManager);
		NhanVienImp nhanVienImp = new NhanVienImp(entityManager);
		LoaiSanPhamImp loaiSanPhamImp = new LoaiSanPhamImp(entityManager);
		SanPhamImp sanphamImp = new SanPhamImp(entityManager);

		List<SanPham> dsSanPhams = sanphamImp.timKiemSanPham("1", "", "");
		for (SanPham sanPham : dsSanPhams) {
			System.out.println(sanPham);
		}
		
	    entityManager.close();
	}
}
