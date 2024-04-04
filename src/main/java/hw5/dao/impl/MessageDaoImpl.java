package hw5.dao.impl;

import hw5.dao.Dao;
import hw5.dao.MessageDao;
import hw5.model.Chat;
import hw5.model.Message;
import hw5.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static hw5.util.EntityGraphHintUtil.*;
import static org.hibernate.graph.GraphSemantic.LOAD;

@Repository
@RequiredArgsConstructor
public class MessageDaoImpl implements Dao<Long, Message>, MessageDao {
    private final SessionFactory sessionFactory;

    @Override
    public Optional<Message> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();

        return Optional.ofNullable(
                session.find(Message.class, id, Map.of(LOAD.getJakartaHintName(),
                        getMessageWithUserAndChatHint(session))));
    }

    @Override
    public List<Message> findAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT m " +
                        "FROM Message m ", Message.class)
                .setHint(LOAD.getJakartaHintName(), getMessageWithUserAndChatHint(session))
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        var message = session.get(Message.class, id);
        if (message != null) {
            session.remove(message);
        }
    }

    @Override
    public Message add(Message entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);
        return entity;
    }

    @Override
    public Message update(Message entity) {
        Session session = sessionFactory.getCurrentSession();
        var message = session.get(Message.class, entity.getId());
        message.setText(entity.getText());
        session.flush();
        return entity;
    }

    @Override
    public List<Message> findUserMessageById(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT u " +
                        "FROM User u " +
                        "WHERE u.id = ?1", User.class)
                .setParameter(1, userId)
                .setHint(LOAD.getJakartaHintName(), getUserMessageHint(session))
                .getSingleResult()
                .getMessages();
    }

    @Override
    public List<Message> findChatMessageById(Long chatId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT c " +
                        "FROM Chat c " +
                        "WHERE c.id = ?1", Chat.class)
                .setParameter(1, chatId)
                .setHint(LOAD.getJakartaHintName(), getChatMessageHint(session))
                .getSingleResult()
                .getMessages();
    }
}
