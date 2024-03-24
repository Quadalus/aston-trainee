package hw2.service.impl;

import hw2.dao.Dao;
import hw2.dao.impl.ChatDaoImpl;
import hw2.dto.ChatDto;
import hw2.exception.EntityNotFoundException;
import hw2.model.Chat;
import hw2.service.Service;
import hw2.util.mapper.ChatDtoMapper;

import java.util.List;

public class ChatServiceImpl implements Service<ChatDto> {
    private static final ChatServiceImpl INSTANCE = new ChatServiceImpl();
    private final Dao<Long, Chat> chatDao = ChatDaoImpl.getInstance();

    private ChatServiceImpl() {
    }

    public static ChatServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ChatDto findById(Long id) {
        var user = getChat(id);
        return ChatDtoMapper.toDto(user);
    }

    @Override
    public List<ChatDto> findAll() {
        return chatDao.findAll().stream()
                .map(ChatDtoMapper::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        chatDao.deleteById(id);
    }

    @Override
    public ChatDto add(ChatDto chat) {
        var chatToAdd = ChatDtoMapper.fromDto(chat);
        var savedChat = chatDao.add(chatToAdd);
        return ChatDtoMapper.toDto(savedChat);
    }

    @Override
    public ChatDto update(ChatDto chat, Long id) {
        var chatToUpdate = getChat(id);
        var updatedChat = chatDao.update(ChatDtoMapper.fromDto(chat, id));
        return ChatDtoMapper.toDto(updatedChat);
    }

    private Chat getChat(Long id) {
        return chatDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chat with id:%d, not found".formatted(id)));
    }
}
