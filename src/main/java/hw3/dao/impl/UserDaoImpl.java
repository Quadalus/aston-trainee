package hw3.dao.impl;

import hw3.dao.Dao;
import hw3.model.User;
import hw3.util.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        var sql = """
                SELECT *
                FROM users
                WHERE user_id = ?
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Optional.ofNullable(buildUser(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        var sql = """
                SELECT *
                FROM users;
                """;
        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();

            var users = new ArrayList<User>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        var addSql = """
                DELETE FROM users
                WHERE user_id = ?;
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(addSql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User add(User entity) {
        var addSql = """
                INSERT INTO users(user_email, user_name)
                VALUES(?, ?)
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(addSql)) {
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getUsername());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("user_id", Long.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public User update(User entity) {
        var addSql = """
                UPDATE users
                SET user_name = ?,
                    user_email = ?
                WHERE user_id = ?;
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(addSql)) {
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getObject("user_id", Long.class),
                resultSet.getObject("user_name", String.class),
                resultSet.getObject("user_email", String.class)
        );
    }
}
