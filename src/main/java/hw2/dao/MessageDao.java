package hw2.dao;

import hw2.model.Message;

import java.util.List;

public interface MessageDao {
    List<Message> findUserMessageById(Long userId);

    List<Message> findChatMessageById(Long chatId);
}
