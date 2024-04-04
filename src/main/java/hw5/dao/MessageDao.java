package hw5.dao;

import hw5.model.Message;

import java.util.List;

public interface MessageDao {
    List<Message> findUserMessageById(Long userId);

    List<Message> findChatMessageById(Long chatId);
}
