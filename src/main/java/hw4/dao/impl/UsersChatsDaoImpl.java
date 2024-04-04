package hw4.dao.impl;

import hw4.dao.UsersChatsDao;
import hw4.model.Chat;
import hw4.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hw4.util.EntityGraphHintUtil.getChatWithUsersHint;
import static hw4.util.EntityGraphHintUtil.getUserWithChatsHint;

@Repository
@RequiredArgsConstructor
public class UsersChatsDaoImpl implements UsersChatsDao {
    private final SessionFactory sessionFactory;

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT u FROM User u", User.class)
                .setHint(GraphSemantic.LOAD.getJakartaHintName(), getUserWithChatsHint(session))
                .getResultList();
    }

    @Override
    public List<User> findUserChatsById(long userId) {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT u " +
                        "FROM User u " +
                        "WHERE u.id=?1", User.class)
                .setHint(GraphSemantic.LOAD.getJakartaHintName(), getUserWithChatsHint(session))
                .setParameter(1, userId)
                .getResultList();
    }

    @Override
    public List<Chat> findChatUsersById(long chatId) {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT c " +
                        "FROM Chat c " +
                        "WHERE c.id =?1", Chat.class)
                .setHint(GraphSemantic.LOAD.getJakartaHintName(), getChatWithUsersHint(session))
                .setParameter(1, chatId)
                .getResultList();
    }
}
