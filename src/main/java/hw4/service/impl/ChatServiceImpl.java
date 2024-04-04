package hw4.service.impl;

import hw4.dao.Dao;
import hw4.dto.ChatDto;
import hw4.exception.EntityNotFoundException;
import hw4.model.Chat;
import hw4.service.CommonService;
import hw4.util.mapper.ChatDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatServiceImpl implements CommonService<ChatDto> {
    private final Dao<Long, Chat> chatDaoImpl;

    @Override
    public ChatDto findById(Long id) {
        var user = getChat(id);
        return ChatDtoMapper.toDto(user);
    }

    @Override
    public List<ChatDto> findAll() {
        return chatDaoImpl.findAll().stream()
                .map(ChatDtoMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        chatDaoImpl.deleteById(id);
    }

    @Override
    @Transactional
    public ChatDto add(ChatDto chat) {
        var chatToAdd = ChatDtoMapper.fromDto(chat);
        var savedChat = chatDaoImpl.add(chatToAdd);
        return ChatDtoMapper.toDto(savedChat);
    }

    @Override
    @Transactional
    public ChatDto update(ChatDto chat, Long id) {
        var chatToUpdate = getChat(id);
        var updatedChat = chatDaoImpl.update(ChatDtoMapper.fromDto(chat, id));
        return ChatDtoMapper.toDto(updatedChat);
    }

    private Chat getChat(Long id) {
        return chatDaoImpl.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chat with id:%d, not found".formatted(id)));
    }
}
