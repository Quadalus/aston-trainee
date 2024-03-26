package hw3.dao.impl;

import hw3.dao.Dao;
import hw3.dao.MessageDao;
import hw3.model.Chat;
import hw3.model.Message;
import hw3.model.User;
import hw3.util.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageDaoImpl implements Dao<Long, Message>, MessageDao {
    private static final MessageDaoImpl INSTANCE = new MessageDaoImpl();

    private MessageDaoImpl() {
    }

    public static MessageDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Message> findById(Long id) {
        var sql = """
                SELECT message_id, message_text, message_created_on, u.user_id, u.user_name, u.user_email, c.chat_id, c.chat_title, chat_created_on
                FROM message m
                JOIN chats c ON c.chat_id = m.message_chat_id
                JOIN users u ON u.user_id = m.message_user_id
                WHERE message_id = ?
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            var user = buildUser(resultSet);
            var chat = buildChat(resultSet);

            return Optional.ofNullable(buildMessage(resultSet, user, chat));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Message> findAll() {
        var sql = """
                SELECT message_id, message_text, message_created_on, u.user_id, u.user_name, u.user_email, c.chat_id, c.chat_title, chat_created_on
                FROM message m
                JOIN chats c ON c.chat_id = m.message_chat_id
                JOIN users u ON u.user_id = m.message_user_id
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();
            var messages = new ArrayList<Message>();

            while (resultSet.next()) {
                var user = buildUser(resultSet);
                var chat = buildChat(resultSet);
                messages.add(buildMessage(resultSet, user, chat));
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        var addSql = """
                DELETE FROM message
                WHERE message_id = ?;
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
    public Message add(Message entity) {
        var addSql = """
                INSERT INTO message(message_text, message_user_id, message_chat_id, message_created_on)
                VALUES(?, ?, ?, ?)
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(addSql)) {
            preparedStatement.setString(1, entity.getText());
            preparedStatement.setLong(2, entity.getUser().getId());
            preparedStatement.setLong(3, entity.getChat().getId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(entity.getCreationOn()));
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
    public Message update(Message entity) {
        var addSql = """
                UPDATE message
                SET message_text = ?,
                    message_created_on = ?
                WHERE message_id = ?;
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(addSql)) {
            preparedStatement.setString(1, entity.getText());
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

    @Override
    public List<Message> findUserMessageById(Long userId) {
        var sql = """
                SELECT message_id, message_text,message_created_on, u.user_id, u.user_name, u.user_email, c.chat_id, c.chat_title, chat_created_on
                FROM message m
                JOIN chats c ON c.chat_id = m.message_chat_id
                JOIN users u ON u.user_id = m.message_user_id
                WHERE u.user_id = ?;
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(sql)) {
            preparedStatement.setLong(1, userId);
            var resultSet = preparedStatement.executeQuery();
            var messages = new ArrayList<Message>();

            while (resultSet.next()) {
                var user = buildUser(resultSet);
                var chat = buildChat(resultSet);
                messages.add(buildMessage(resultSet, user, chat));
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Message> findChatMessageById(Long chatId) {
        var sql = """
                SELECT message_id, message_text,message_created_on, u.user_id, u.user_name, u.user_email, c.chat_id, c.chat_title, chat_created_on
                FROM message m
                JOIN chats c ON c.chat_id = m.message_chat_id
                JOIN users u ON u.user_id = m.message_user_id
                WHERE c.chat_id = ?;
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(sql)) {
            preparedStatement.setLong(1, chatId);
            var resultSet = preparedStatement.executeQuery();
            var messages = new ArrayList<Message>();

            while (resultSet.next()) {
                var user = buildUser(resultSet);
                var chat = buildChat(resultSet);
                messages.add(buildMessage(resultSet, user, chat));
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getObject("user_id", Long.class),
                resultSet.getObject("user_name", String.class),
                resultSet.getObject("user_email", String.class)
        );
    }

    private Chat buildChat(ResultSet resultSet) throws SQLException {
        return new Chat(
                resultSet.getObject("chat_id", Long.class),
                resultSet.getObject("chat_title", String.class),
                resultSet.getObject("chat_created_on", LocalDateTime.class)
        );
    }

    private Message buildMessage(ResultSet resultSet, User user, Chat chat) throws SQLException {
        return new Message(resultSet.getObject("message_id", Long.class),
                resultSet.getObject("message_text", String.class),
                user,
                chat,
                resultSet.getObject("message_created_on", LocalDateTime.class)
        );
    }
}
