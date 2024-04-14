package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA_ORM_QLMuaBan");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	public static EntityManager getEntityManager() {
		return entityManager;
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
	public static void Shutdown() {
		entityManagerFactory.close();
	}
}
