package hw3.dao;

import hw3.model.UsersChats;

public interface UsersChatsDao {
    UsersChats findAll();

    UsersChats findUserChatsById(long userId);

    UsersChats findChatUsersById(long chatId);
}
