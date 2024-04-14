package run;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import util.HibernateUtil;

public class RunDB {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
		
	}
}
