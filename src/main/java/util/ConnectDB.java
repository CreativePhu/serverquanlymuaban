package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectDB {
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public ConnectDB() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("JPA_ORM_QLMuaBan");
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void Shutdown() {
		entityManagerFactory.close();
	}
}
