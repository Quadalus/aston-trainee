package hw2.dao.impl;

import hw2.dao.Dao;
import hw2.model.Chat;
import hw2.util.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        var sql = """
                SELECT *
                FROM chats
                WHERE id = ?
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Optional.of(buildChat(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Chat> findAll() {
        var sql = """
                SELECT *
                FROM chats;
                """;
        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();

            var chats = new ArrayList<Chat>();
            while (resultSet.next()) {
                chats.add(buildChat(resultSet));
            }
            return chats;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        var addSql = """
                DELETE FROM chats
                WHERE id = ?;
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
    public Chat add(Chat entity) {
        var addSql = """
                INSERT INTO chats(title, created_on)
                VALUES(?, ?)
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(addSql)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getCreationOn()));
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
    public Chat update(Chat entity) {
        var addSql = """
                UPDATE chats
                SET title = ?,
                created_on = ?
                WHERE id = ?;
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(addSql)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getCreationOn()));
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    private Chat buildChat(ResultSet resultSet) throws SQLException {
        return new Chat(
                resultSet.getObject("chat_id", Long.class),
                resultSet.getObject("chat_title", String.class),
                resultSet.getObject("chat_created_on", LocalDateTime.class)
        );
    }
}
