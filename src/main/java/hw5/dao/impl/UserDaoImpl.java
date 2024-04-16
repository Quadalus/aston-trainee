package hw5.dao.impl;

import hw5.dao.Dao;
import hw5.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements Dao<Long, User> {
    private final SessionFactory sessionFactory;

    @Override
    public Optional<User> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();

        return Optional.ofNullable(session.get(User.class, id));
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select u From User u", User.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();

        var user = session.get(User.class, id);
        if (user != null) {
            session.remove(user);
        }
    }

    @Override
    public User add(User entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);
        return entity;
    }

    @Override
    public User update(User entity) {
        Session session = sessionFactory.getCurrentSession();

        var user = session.get(User.class, entity.getId());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        return entity;
    }
}
