package vn.thienphu.serverquanlybanhang.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
	private static EntityManagerFactory entityManagerFactory = getEntityManagerFactory();

	public static EntityManagerFactory getEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("JPA_ORM_QLMuaBan");
	}
	
	public static void Shutdown() {
		entityManagerFactory.close();
	}
}
