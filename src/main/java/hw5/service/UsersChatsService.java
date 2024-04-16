package hw5.service;

import hw5.dto.UsersChatsDto;

public interface UsersChatsService {
    UsersChatsDto findAll();

    UsersChatsDto findUserChatsById(long userId);

    UsersChatsDto findChatUsersById(long chatId);
}
