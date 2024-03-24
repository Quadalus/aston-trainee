package hw2.service.impl;

import hw2.dao.Dao;
import hw2.dao.MessageDao;
import hw2.dao.impl.MessageDaoImpl;
import hw2.dto.MessageDto;
import hw2.exception.EntityNotFoundException;
import hw2.model.Message;
import hw2.service.MessageService;
import hw2.util.mapper.MessageDtoMapper;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    private static final MessageServiceImpl INSTANCE = new MessageServiceImpl();
    private final Dao<Long, Message> dao = MessageDaoImpl.getInstance();
    private final MessageDao messageDao = MessageDaoImpl.getInstance();

    private MessageServiceImpl() {
    }

    public static MessageServiceImpl getInstance() {
        return INSTANCE;
    }

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
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public MessageDto add(MessageDto chat, Long userId, Long chatId) {
        var chatToAdd = MessageDtoMapper.fromDto(chat, userId, chatId, chatId);
        var savedChat = dao.add(chatToAdd);
        return MessageDtoMapper.toDto(savedChat);
    }

    @Override
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
