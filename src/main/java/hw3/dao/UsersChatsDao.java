package hw3.dao;

import hw3.model.Chat;
import hw3.model.User;

import java.util.List;

public interface UsersChatsDao {
    List<User> findAll();

    List<User> findUserChatsById(long userId);

    List<Chat> findChatUsersById(long chatId);
}
