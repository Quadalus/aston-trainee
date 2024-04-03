package hw3.dao.impl;

import hw3.dao.Dao;
import hw3.dao.MessageDao;
import hw3.model.Chat;
import hw3.model.Message;
import hw3.model.User;
import hw3.util.SessionFactoryUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static hw3.util.EntityGraphHintUtil.*;
import static org.hibernate.graph.GraphSemantic.LOAD;

public class MessageDaoImpl implements Dao<Long, Message>, MessageDao {
    private static final MessageDaoImpl INSTANCE = new MessageDaoImpl();

    private MessageDaoImpl() {
    }

    public static MessageDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Message> findById(Long id) {
        Session session = SessionFactoryUtil.open();
        Optional<Message> message;

        try {
            session.beginTransaction();
            message = Optional.of(
                    session.find(Message.class, id, Map.of(LOAD.getJakartaHintName(), getMessageWithUserAndChatHint(session)))
            );
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return message;
    }

    @Override
    public List<Message> findAll() {
        Session session = SessionFactoryUtil.open();
        List<Message> messages;

        try {
            session.beginTransaction();
            messages = session.createQuery("SELECT m " +
                            "FROM Message m ", Message.class)
                    .setHint(LOAD.getJakartaHintName(), getMessageWithUserAndChatHint(session))
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return messages;
    }

    @Override
    public void deleteById(Long id) {
        Session session = SessionFactoryUtil.open();

        try {
            session.beginTransaction();
            var message = session.get(Message.class, id);
            if (message != null) {
                session.remove(message);
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
    public Message add(Message entity) {
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
    public Message update(Message entity) {
        Session session = SessionFactoryUtil.open();

        try {
            session.beginTransaction();
            var message = session.get(Message.class, entity.getId());
            message.setText(entity.getText());
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
    public List<Message> findUserMessageById(Long userId) {
        Session session = SessionFactoryUtil.open();
        List<Message> messages;
        try {
            session.beginTransaction();
            messages = session.createQuery("SELECT u " +
                    "FROM User u " +
                    "WHERE u.id = ?1",User.class)
                    .setParameter(1,userId)
                    .setHint(LOAD.getJakartaHintName(), getUserMessageHint(session))
                    .getSingleResult()
                    .getMessages();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return messages;
    }

    @Override
    public List<Message> findChatMessageById(Long chatId) {
        Session session = SessionFactoryUtil.open();
        List<Message> messages;
        try {
            session.beginTransaction();
            messages = session.createQuery("SELECT c " +
                            "FROM Chat c " +
                            "WHERE c.id = ?1", Chat.class)
                    .setParameter(1,chatId)
                    .setHint(LOAD.getJakartaHintName(), getChatMessageHint(session))
                    .getSingleResult()
                    .getMessages();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return messages;
    }
}
