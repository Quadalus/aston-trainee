package hw2.service;

import hw2.dto.UsersChatsDto;

public interface UsersChatsService {
    UsersChatsDto findAll();

    UsersChatsDto findUserChatsById(long userId);

    UsersChatsDto findChatUsersById(long chatId);
}
