package hw3.service;

import hw3.dto.UsersChatsDto;

public interface UsersChatsService {
    UsersChatsDto findAll();

    UsersChatsDto findUserChatsById(long userId);

    UsersChatsDto findChatUsersById(long chatId);
}
