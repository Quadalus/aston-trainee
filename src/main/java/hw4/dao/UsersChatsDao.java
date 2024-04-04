package hw4.dao;

import hw4.model.Chat;
import hw4.model.User;

import java.util.List;

public interface UsersChatsDao {
    List<User> findAll();

    List<User> findUserChatsById(long userId);

    List<Chat> findChatUsersById(long chatId);
}
