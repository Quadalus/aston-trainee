package hw4.service;

import hw4.dto.UsersChatsDto;

public interface UsersChatsService {
    UsersChatsDto findAll();

    UsersChatsDto findUserChatsById(long userId);

    UsersChatsDto findChatUsersById(long chatId);
}
