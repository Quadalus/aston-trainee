package hw5.service;

import hw5.dto.MessageDto;

import java.util.List;

public interface MessageService {
    MessageDto findById(Long id);

    List<MessageDto> findAll();

    void deleteById(Long id);

    MessageDto add(MessageDto object, Long userId, Long chatId);

    MessageDto update(MessageDto object, Long messageId, Long userId, Long chatId);

    List<MessageDto> findUserMessageById(Long userId);

    List<MessageDto> findChatMessageById(Long chatId);
}
