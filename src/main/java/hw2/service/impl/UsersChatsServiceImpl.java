package hw2.service.impl;

import hw2.dao.UsersChatsDao;
import hw2.dao.impl.UsersChatsDaoImpl;
import hw2.dto.UsersChatsDto;
import hw2.service.UsersChatsService;
import hw2.util.mapper.UsersChatsDtoMapper;

public class UsersChatsServiceImpl implements UsersChatsService {
    private static final UsersChatsServiceImpl INSTANCE = new UsersChatsServiceImpl();
    private final UsersChatsDao usersChatsDao = UsersChatsDaoImpl.getInstance();

    @Override
    public UsersChatsDto findAll() {
        return UsersChatsDtoMapper.toDto(usersChatsDao.findAll());
    }

    @Override
    public UsersChatsDto findUserChatsById(long userId) {
        return UsersChatsDtoMapper.toDto(usersChatsDao.findUserChatsById(userId));
    }

    @Override
    public UsersChatsDto findChatUsersById(long chatId) {
        return UsersChatsDtoMapper.toDto(usersChatsDao.findChatUsersById(chatId));
    }

    public static UsersChatsServiceImpl getInstance() {
        return INSTANCE;
    }
}
