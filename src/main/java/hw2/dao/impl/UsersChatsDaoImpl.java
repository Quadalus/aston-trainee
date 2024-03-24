package hw2.dao.impl;

import hw2.dao.UsersChatsDao;
import hw2.model.Chat;
import hw2.model.User;
import hw2.model.UsersChats;
import hw2.util.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsersChatsDaoImpl implements UsersChatsDao {
    private static final UsersChatsDaoImpl INSTANCE = new UsersChatsDaoImpl();

    private UsersChatsDaoImpl() {
    }

    public static UsersChatsDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public UsersChats findAll() {
        var sql = """
                SELECT u.user_id, u.user_name, u.user_email, c.chat_id, c.chat_title, c.chat_created_on
                FROM users_chats
                JOIN chats c ON c.chat_id = users_chats.chat_id
                JOIN users u ON u.user_id = users_chats.user_id;
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();

            var users = new ArrayList<User>();
            var chats = new ArrayList<Chat>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
                chats.add(buildChat(resultSet));
            }
            return buildUserChats(users, chats);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UsersChats findUserChatsById(long userId) {
        var sql = """
                SELECT u.user_id, u.user_name, u.user_email, c.chat_id, c.chat_title, c.chat_created_on
                FROM users_chats
                JOIN chats c ON c.chat_id = users_chats.chat_id
                JOIN users u ON u.user_id = users_chats.user_id
                WHERE u.user_id = ?;
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(sql)) {
            preparedStatement.setLong(1, userId);
            var resultSet = preparedStatement.executeQuery();

            var users = new ArrayList<User>();
            var chats = new ArrayList<Chat>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
                chats.add(buildChat(resultSet));
            }
            return buildUserChats(List.of(users.getFirst()), chats);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UsersChats findChatUsersById(long chatId) {
        var sql = """
                SELECT u.user_id, u.user_name, u.user_email, c.chat_id, c.chat_title, c.chat_created_on
                FROM users_chats
                JOIN chats c ON c.chat_id = users_chats.chat_id
                JOIN users u ON u.user_id = users_chats.user_id
                WHERE c.chat_id = ?;
                """;

        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement(sql)) {
            preparedStatement.setLong(1, chatId);
            var resultSet = preparedStatement.executeQuery();

            var users = new ArrayList<User>();
            var chats = new ArrayList<Chat>();
            while (resultSet.next()) {
                chats.add(buildChat(resultSet));
                users.add(buildUser(resultSet));
            }
            return buildUserChats(users, List.of(chats.getFirst()));
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

    private UsersChats buildUserChats(List<User> users, List<Chat> chats) throws SQLException {
        return new UsersChats(
                users, chats
        );
    }
}
