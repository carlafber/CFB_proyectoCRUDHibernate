package com.example.carla_delafuentebernardino_hibernate1n.util;

import com.example.carla_delafuentebernardino_hibernate1n.classes.Coche;
import com.example.carla_delafuentebernardino_hibernate1n.classes.Multas;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	static SessionFactory factory = null;
	static {
		Configuration cfg = new Configuration();
		cfg.configure("configuration/hibernate.cfg.xml");

		cfg.addAnnotatedClass(Coche.class);
		cfg.addAnnotatedClass(Multas.class);

		factory = cfg.buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	
	public static Session getSession() {
		return factory.openSession();
	}
	
	
}
