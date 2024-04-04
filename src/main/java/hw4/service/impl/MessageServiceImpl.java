package hw4.service.impl;

import hw4.dao.Dao;
import hw4.dao.MessageDao;
import hw4.dto.MessageDto;
import hw4.exception.EntityNotFoundException;
import hw4.model.Message;
import hw4.service.MessageService;
import hw4.util.mapper.MessageDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final Dao<Long, Message> dao;
    private final MessageDao messageDao;

    @Override
    public MessageDto findById(Long id) {
        var user = getMessage(id);
        return MessageDtoMapper.toDto(user);
    }

    @Override
    public List<MessageDto> findAll() {
        return dao.findAll().stream()
                .map(MessageDtoMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional
    public MessageDto add(MessageDto chat, Long userId, Long chatId) {
        var chatToAdd = MessageDtoMapper.fromDto(chat, userId, chatId);
        var savedChat = dao.add(chatToAdd);
        return MessageDtoMapper.toDto(savedChat);
    }

    @Override
    @Transactional
    public MessageDto update(MessageDto chat, Long messageId, Long chatId, Long userId) {
        var chatToUpdate = getMessage(messageId);
        var updatedChat = dao.update(MessageDtoMapper.fromDto(chat, messageId, userId, chatId));
        return MessageDtoMapper.toDto(updatedChat);
    }

    @Override
    public List<MessageDto> findUserMessageById(Long userId) {
        return messageDao.findUserMessageById(userId)
                .stream()
                .map(MessageDtoMapper::toDto)
                .toList();
    }

    @Override
    public List<MessageDto> findChatMessageById(Long chatId) {
        return messageDao.findChatMessageById(chatId)
                .stream()
                .map(MessageDtoMapper::toDto)
                .toList();
    }

    private Message getMessage(Long id) {
        return dao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Message with id:%d, not found".formatted(id)));
    }
}
