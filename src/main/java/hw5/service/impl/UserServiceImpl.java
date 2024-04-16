package hw5.service.impl;

import hw5.dao.Dao;
import hw5.dto.UserDto;
import hw5.exception.EntityNotFoundException;
import hw5.model.User;
import hw5.service.CommonService;
import hw5.util.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements CommonService<UserDto> {
    private final Dao<Long, User> userDaoImpl;

    @Override
    public UserDto findById(Long id) {
        var user = getUser(id);
        return UserDtoMapper.toDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        return userDaoImpl.findAll().stream()
                .map(UserDtoMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDaoImpl.deleteById(id);
    }

    @Override
    @Transactional
    public UserDto add(UserDto user) {
        var userToAdd = UserDtoMapper.fromDto(user);
        var savedUser = userDaoImpl.add(userToAdd);
        return UserDtoMapper.toDto(savedUser);
    }

    @Override
    @Transactional
    public UserDto update(UserDto user, Long id) {
        var userToUpdate = getUser(id);
        var updatedUser = userDaoImpl.update(UserDtoMapper.fromDto(user, id));
        return UserDtoMapper.toDto(updatedUser);
    }

    private User getUser(Long id) {
        return userDaoImpl.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id:%d, not found".formatted(id)));
    }
}
