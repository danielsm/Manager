package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;

public class DAO {
	
	private static final SessionFactory sessionFactory;
	 
	 static{
	  
	   sessionFactory = new  AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
	 }
	 //retorna uma sessao de comunicacao com o BD
	 public static Session getSession(){
		 return sessionFactory.openSession();
	 }
}
