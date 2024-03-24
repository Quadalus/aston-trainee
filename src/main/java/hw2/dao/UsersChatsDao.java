package hw2.dao;

import hw2.model.UsersChats;

public interface UsersChatsDao {
    UsersChats findAll();

    UsersChats findUserChatsById(long userId);

    UsersChats findChatUsersById(long chatId);
}
