package hw5.service.impl;

import hw5.dao.UsersChatsDao;
import hw5.dto.UsersChatsDto;
import hw5.service.UsersChatsService;
import hw5.util.mapper.UsersChatsDtoMapper;
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
