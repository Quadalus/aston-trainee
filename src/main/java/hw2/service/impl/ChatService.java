package hw2.service.impl;

import hw2.dao.impl.ChatDao;
import hw2.dto.ChatDto;
import hw2.exception.ChatNotFoundException;
import hw2.exception.UserNotFoundException;
import hw2.model.Chat;
import hw2.service.Service;
import hw2.util.mapper.ChatDtoMapper;

import java.util.List;

public class ChatService implements Service<ChatDto> {
    private static final ChatService INSTANCE = new ChatService();
    private final ChatDao chatDao = ChatDao.getInstance();

    private ChatService() {
    }

    public static ChatService getInstance() {
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
                .orElseThrow(() -> new ChatNotFoundException("Chat with id:%d, not found".formatted(id)));
    }
}
