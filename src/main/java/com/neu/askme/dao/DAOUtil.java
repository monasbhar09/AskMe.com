package com.neu.askme.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DAOUtil {

	private static final Logger log = Logger.getAnonymousLogger();

	private static final ThreadLocal sessionThread = new ThreadLocal();
	private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.buildSessionFactory();

	protected DAOUtil() {
	}

	public static Session getSession() {
		Session session = (Session) DAOUtil.sessionThread.get();

		if (session == null) {
			session = sessionFactory.openSession();
			DAOUtil.sessionThread.set(session);
		}
		return session;
	}

	protected void begin() {
		getSession().beginTransaction();
	}

	protected void commit() {
		getSession().getTransaction().commit();
	}

	protected void rollback() {
		try {
			getSession().getTransaction().rollback();
		} catch (HibernateException e) {
			log.log(Level.WARNING, "Cannot rollback", e);
		}
		try {
			getSession().close();
		} catch (HibernateException e) {
			log.log(Level.WARNING, "Cannot close", e);
		}
		DAOUtil.sessionThread.set(null);
	}

	public static void close() {
		getSession().close();
		DAOUtil.sessionThread.set(null);
	}
}
