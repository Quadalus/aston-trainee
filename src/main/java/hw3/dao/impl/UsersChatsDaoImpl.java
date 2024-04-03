package hw3.dao.impl;

import hw3.dao.UsersChatsDao;
import hw3.model.Chat;
import hw3.model.User;
import hw3.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.graph.GraphSemantic;

import java.util.List;

import static hw3.util.EntityGraphHintUtil.getChatWithUsersHint;
import static hw3.util.EntityGraphHintUtil.getUserWithChatsHint;

public class UsersChatsDaoImpl implements UsersChatsDao {
    private static final UsersChatsDaoImpl INSTANCE = new UsersChatsDaoImpl();

    private UsersChatsDaoImpl() {
    }

    public static UsersChatsDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<User> findAll() {
        Session session = SessionFactoryUtil.open();
        List<User> user;

        try {
            session.beginTransaction();
            user = session.createQuery("SELECT u FROM User u", User.class)
                    .setHint(GraphSemantic.LOAD.getJakartaHintName(), getUserWithChatsHint(session))
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public List<User> findUserChatsById(long userId) {
        Session session = SessionFactoryUtil.open();
        List<User> user;

        try {
            session.beginTransaction();

            user = session.createQuery("SELECT u " +
                            "FROM User u " +
                            "WHERE u.id=?1", User.class)
                    .setHint(GraphSemantic.LOAD.getJakartaHintName(), getUserWithChatsHint(session))
                    .setParameter(1, userId)
                    .getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public List<Chat> findChatUsersById(long chatId) {
        Session session = SessionFactoryUtil.open();
        List<Chat> chat;

        try {
            session.beginTransaction();

            chat = session.createQuery("SELECT c " +
                            "FROM Chat c " +
                            "WHERE c.id =?1", Chat.class)
                    .setHint(GraphSemantic.LOAD.getJakartaHintName(), getChatWithUsersHint(session))
                    .setParameter(1, chatId)
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return chat;
    }
}
