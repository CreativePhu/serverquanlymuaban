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
//
//		Quyen quyen = new Quyen("Staff");
//		Quyen quyen1 = new Quyen("Manager");
//
//		quyenImp.taoQuyen(quyen);
//		quyenImp.taoQuyen(quyen1);
		
//		Quyen quyen2 = quyenImp.timQuyenBangTen("Staff").get(0);
//		System.out.println(quyen2);
		
		NhanVien nhanVien = new NhanVien("Tran The Bao", "0348579651", false);
		Quyen newQuyen = quyenImp.timQuyenBangTen("Staff").get(0);
        TaiKhoan taiKhoan = new TaiKhoan("0481231382", "123123aA", false, newQuyen);
        nhanVien.setTaiKhoan(taiKhoan);
		nhanVienImp.taoNhanVien(nhanVien);

//		List<Quyen> dsQuyen = quyenImp.layDanhSachQuyen();
//		for (Quyen q : dsQuyen) {
//			System.out.println(q);
//		}
		
//		LoaiSanPham loaiSanPham = new LoaiSanPham("Đồ Uống");
//		LoaiSanPham loaiSanPham1 = new LoaiSanPham("Hải Sản");
//		loaiSanPhamImp.taoLoaiSanPham(loaiSanPham);
//		loaiSanPhamImp.taoLoaiSanPham(loaiSanPham1);
//
//		SanPham sanPham = new SanPham("Pessi", 10000);
//		LoaiSanPham newLoaiSanPham = loaiSanPhamImp.timLoaiSanPhamBangTen("Đồ Uống").get(0);
//		sanPham.setLoaiSanPham(newLoaiSanPham);
//		sanphamImp.taoSanPham(sanPham);

//		List<NhanVien> dsNhanVien = nhanVienImp.timKiemNhanVien("","Phú", "","Nam");
//		for (NhanVien nv : dsNhanVien) {
//			System.out.println(nv);
//		}
		
	    entityManager.close();
	}
}
