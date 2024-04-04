package hw5.dao.impl;

import hw5.dao.Dao;
import hw5.model.Chat;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChatDaoImpl implements Dao<Long, Chat> {
    private final SessionFactory sessionFactory;

    @Override
    public Optional<Chat> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();

        return Optional.ofNullable(session.find(Chat.class, id));
    }

    @Override
    public List<Chat> findAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT c FROM Chat c", Chat.class)
                .list();
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();

        var chat = session.get(Chat.class, id);
        if (chat != null) {
            session.remove(chat);
        }
    }

    @Override
    public Chat add(Chat entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);
        return entity;
    }

    @Override
    public Chat update(Chat entity) {
        Session session = sessionFactory.getCurrentSession();
        var chat = session.get(Chat.class, entity.getId());
        chat.setTitle(entity.getTitle());
        return entity;
    }
}
