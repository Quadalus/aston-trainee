package hw5.dao;

import hw5.model.Chat;
import hw5.model.User;

import java.util.List;

public interface UsersChatsDao {
    List<User> findAll();

    List<User> findUserChatsById(long userId);

    List<Chat> findChatUsersById(long chatId);
}
