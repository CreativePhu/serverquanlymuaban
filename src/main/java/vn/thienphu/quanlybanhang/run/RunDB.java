package vn.thienphu.quanlybanhang.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RunDB {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA_ORM_QLBanHang");
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		Quyen quyen = new Quyen("ADMIN");
//		TaiKhoan taiKhoan = new TaiKhoan("phutot111", false);
//		taiKhoan.themQuyen(quyen);
//		NhanVien nhanVien = new NhanVien("Ngô Thiên Phú", "0348191482", "phutot1111@gmail.com");
//		nhanVien.setTaiKhoan(taiKhoan);
//		session.persist(nhanVien);
//		session.getTransaction().commit();
//		session.close();
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		LoaiSanPham loaiSanPham = new LoaiSanPham("Bàn ghế");
//		SanPham sanPham = new SanPham("Bàn gỗ", 120000);
//		sanPham.addLoaiSanPham(loaiSanPham);
//		String image = convertImageToBase64("C:\\Users\\Acer\\OneDrive\\Hình ảnh\\Saved Pictures\\188A1388.jpg");
//		Anh anh = new Anh(image);
//		sanPham.setAnh(anh);
//		session.persist(sanPham);
//		session.getTransaction().commit();
//		session.close();
	}
	
	public static String convertImageToBase64(String imagePath) {
        File file = new File(imagePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            // Read image into byte array
            byte[] imageData = new byte[(int) file.length()];
            fis.read(imageData);

            // Encode byte array to Base64 string
            return Base64.getEncoder().encodeToString(imageData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
