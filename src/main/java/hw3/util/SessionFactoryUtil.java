package hw3.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionFactoryUtil {
    private static final SessionFactory INSTANCE = HibernateConfigurationUtil.createSessionFactory();

    private SessionFactoryUtil() {
    }

    public static Session open() {
        return INSTANCE.openSession();
    }

    public static SessionFactory getInstance() {
        return INSTANCE;
    }
}
