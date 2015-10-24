package com.saike.grape.util;

/**
 * 这个是参照反向工程生成的文件编写;反响工程中在这个基础上,对sessionFactory还有进一步的增强;
 */
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	// 这个ThreadLocal是最重要的一个变量;
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static SessionFactory sessionFactory;
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";// 默认配置文件
	private static String CONFIG_FILE_PERSONALDEFINE = "/myconfig/my.cfg.xml";// 自定义配置文件
	// 静态加载
	static {
		try {
			// 创建加载配置文件的对象
			Configuration configuration = new Configuration();
			// 加载默认位置的配置文件;
			// configuration.configure();
			// 加载指定名称的指定位置的配置文件;
			configuration.configure(CONFIG_FILE_PERSONALDEFINE);
			// 创建工厂对象;
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("加载自定义文件,创建SessionFactory失败!!!");
			e.printStackTrace();
		}

	}

	public static Session getSession() {
		Session session = (Session) threadLocal.get();
		if (session == null || !session.isOpen()) {
			// 首先判断静态初始化是否正常加载配置文件
			// 如果session为null,重新加载配置文件
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}

			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}
		return session;
	}

	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);

		if (session != null) {
			session.close();
		}
	}

	public static void rebuildSessionFactory() {
		try {
			// 加载默认配置文件
			Configuration configuration = new Configuration();
			configuration.configure(CONFIG_FILE_LOCATION);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("创建SessionFactory 失败!!!");
			e.printStackTrace();
		}
	}

}
