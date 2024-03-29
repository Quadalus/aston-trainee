package hw2.service.impl;

import hw2.dao.Dao;
import hw2.dao.impl.UserDaoImpl;
import hw2.dto.UserDto;
import hw2.exception.EntityNotFoundException;
import hw2.model.User;
import hw2.service.Service;
import hw2.util.mapper.UserDtoMapper;

import java.util.List;

public class UserServiceImpl implements Service<UserDto> {
    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    private final Dao<Long, User> userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public UserDto findById(Long id) {
        var user = getUser(id);
        return UserDtoMapper.toDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        return userDao.findAll().stream()
                .map(UserDtoMapper::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public UserDto add(UserDto user) {
        var userToAdd = UserDtoMapper.fromDto(user);
        var savedUser = userDao.add(userToAdd);
        return UserDtoMapper.toDto(savedUser);
    }

    @Override
    public UserDto update(UserDto user, Long id) {
        var userToUpdate = getUser(id);
        var updatedUser = userDao.update(UserDtoMapper.fromDto(user, id));
        return UserDtoMapper.toDto(updatedUser);
    }

    private User getUser(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id:%d, not found".formatted(id)));
    }
}
