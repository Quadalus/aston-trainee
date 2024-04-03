package hw3.dao.impl;

import hw3.dao.Dao;
import hw3.model.Chat;
import hw3.util.SessionFactoryUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class ChatDaoImpl implements Dao<Long, Chat> {
    private static final ChatDaoImpl INSTANCE = new ChatDaoImpl();

    private ChatDaoImpl() {
    }

    public static ChatDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Chat> findById(Long id) {
        Session session = SessionFactoryUtil.open();
        Optional<Chat> chat;

        try {
            session.beginTransaction();
            chat = Optional.of(session.find(Chat.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return chat;
    }

    @Override
    public List<Chat> findAll() {
        List<Chat> chats;
        Session session = SessionFactoryUtil.open();

        try {
            session.beginTransaction();
            chats = session.createQuery("SELECT c FROM Chat c", Chat.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return chats;
    }

    @Override
    public void deleteById(Long id) {
        Session session = SessionFactoryUtil.open();

        try {
            session.beginTransaction();
            var chat = session.get(Chat.class, id);
            if (chat != null) {
                session.remove(chat);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Chat add(Chat entity) {
        Session session = SessionFactoryUtil.open();

        try {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public Chat update(Chat entity) {
        Session session = SessionFactoryUtil.open();

        try {
            session.beginTransaction();
            var chat = session.get(Chat.class, entity.getId());
            chat.setTitle(entity.getTitle());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return entity;
    }
}
