package hw4.dao;

import hw4.model.Message;

import java.util.List;

public interface MessageDao {
    List<Message> findUserMessageById(Long userId);

    List<Message> findChatMessageById(Long chatId);
}
