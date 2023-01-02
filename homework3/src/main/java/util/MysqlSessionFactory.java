package util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MysqlSessionFactory {
    private static final String HIBERNATE_CFG_DEFAULT_FILENAME = "hibernate.cfg.xml";
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory(String hibernateCfgFilename) {
        try {
            return new Configuration().configure(hibernateCfgFilename).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("build SessionFactory failed :" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return getSessionFactory(HIBERNATE_CFG_DEFAULT_FILENAME);
    }

    public static SessionFactory getSessionFactory(String hibernateCfgFilename) {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory(hibernateCfgFilename);
        }
        return sessionFactory;
    }
}
