package hw3.service.impl;

import hw3.dao.UsersChatsDao;
import hw3.dao.impl.UsersChatsDaoImpl;
import hw3.dto.UsersChatsDto;
import hw3.service.UsersChatsService;
import hw3.util.mapper.UsersChatsDtoMapper;

public class UsersChatsServiceImpl implements UsersChatsService {
    private static final UsersChatsServiceImpl INSTANCE = new UsersChatsServiceImpl();
    private final UsersChatsDao usersChatsDao = UsersChatsDaoImpl.getInstance();

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

    public static UsersChatsServiceImpl getInstance() {
        return INSTANCE;
    }
}
