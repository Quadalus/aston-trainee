package hw3.util;

import hw3.model.Chat;
import hw3.model.Message;
import hw3.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfigurationUtil {
    private static final Configuration INSTANCE = new Configuration();

    private HibernateConfigurationUtil() {
    }

    static {
        INSTANCE.addAnnotatedClass(User.class);
        INSTANCE.addAnnotatedClass(Chat.class);
        INSTANCE.addAnnotatedClass(Message.class);
        INSTANCE.configure();
    }

    public static SessionFactory createSessionFactory() {
        return INSTANCE.buildSessionFactory();
    }
}
