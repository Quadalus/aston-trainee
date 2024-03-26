package hw3.dao;

import hw3.model.Message;

import java.util.List;

public interface MessageDao {
    List<Message> findUserMessageById(Long userId);

    List<Message> findChatMessageById(Long chatId);
}
