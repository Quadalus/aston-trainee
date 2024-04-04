package hw4.service.impl;

import hw4.dao.UsersChatsDao;
import hw4.dto.UsersChatsDto;
import hw4.service.UsersChatsService;
import hw4.util.mapper.UsersChatsDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("usersChats")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsersChatsServiceImpl implements UsersChatsService {
    private final UsersChatsDao usersChatsDao;

    @Override
    public UsersChatsDto findAll() {
        return UsersChatsDtoMapper.toDtoUser(usersChatsDao.findAll());
    }

    @Override
    public UsersChatsDto findUserChatsById(long userId) {
        return UsersChatsDtoMapper.toDtoUser(usersChatsDao.findUserChatsById(userId));
    }

    @Override
    public UsersChatsDto findChatUsersById(long chatId) {
        return UsersChatsDtoMapper.toDtoChat(usersChatsDao.findChatUsersById(chatId));
    }
}
