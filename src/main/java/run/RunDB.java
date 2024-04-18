package run;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import dao.implement.NhanVienImp;
import dao.implement.QuyenImp;
import dao.implement.TaikhoanImp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.NhanVien;
import model.Quyen;
import model.TaiKhoan;
import util.HibernateUtil;

public class RunDB {
	public static void main(String[] args) {
		
		QuyenImp quyenImp = new QuyenImp();
		NhanVienImp nhanVienImp = new NhanVienImp();
		
		Quyen quyen = new Quyen("Staff");
		Quyen quyen1 = new Quyen("Manager");
		
		quyenImp.taoQuyen(quyen);
		quyenImp.taoQuyen(quyen1);
		
//		Quyen quyen2 = quyenImp.timQuyenBangTen("Staff").get(0);
//		System.out.println(quyen2);
		
		NhanVien nhanVien = new NhanVien("Ngô Thiên Phú", "0348191482", true);
		nhanVien.taoTaiKhoanTuDong();
		nhanVienImp.taoNhanVien(nhanVien);

		List<Quyen> dsQuyen = quyenImp.layDanhSachQuyen();
		for (Quyen q : dsQuyen) {
			System.out.println(q);
		}
	}
}
