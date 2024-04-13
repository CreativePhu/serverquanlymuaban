package vn.thienphu.serverquanlybanhang.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vn.thienphu.serverquanlybanhang.util.HibernateUtil;

public class RunDB {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
		
	}
	
//	public static String convertImageToBase64(String imagePath) {
//        File file = new File(imagePath);
//        try (FileInputStream fis = new FileInputStream(file)) {
//            // Read image into byte array
//            byte[] imageData = new byte[(int) file.length()];
//            fis.read(imageData);
//
//            // Encode byte array to Base64 string
//            return Base64.getEncoder().encodeToString(imageData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
