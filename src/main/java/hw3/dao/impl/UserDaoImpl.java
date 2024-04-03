package hw3.dao.impl;

import hw3.dao.Dao;
import hw3.model.User;
import hw3.util.SessionFactoryUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements Dao<Long, User> {
    private static final UserDaoImpl INSTANCE = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<User> findById(Long id) {
        Session session = SessionFactoryUtil.open();
        Optional<User> user;

        try {
            session.beginTransaction();
            user = Optional.of(session.get(User.class, id));
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
    public List<User> findAll() {
        Session session = SessionFactoryUtil.open();
        List<User> users;

        try {
            session.beginTransaction();
            users = session.createQuery("select u From User u", User.class)
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void deleteById(Long id) {
        Session session = SessionFactoryUtil.open();

        try {
            session.beginTransaction();
            var user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
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
    public User add(User entity) {
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
    public User update(User entity) {
        Session session = SessionFactoryUtil.open();

        try {
            session.beginTransaction();
            var user = session.get(User.class, entity.getId());
            user.setUsername(entity.getUsername());
            user.setEmail(entity.getEmail());
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
