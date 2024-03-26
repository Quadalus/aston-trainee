package hw3.util.mapper;

import hw3.dto.MessageDto;
import hw3.model.Message;

import java.time.LocalDateTime;

public final class MessageDtoMapper {
    private MessageDtoMapper() {
    }

    public static Message fromDto(MessageDto messageDto, Long userId, Long chatId) {
        var message = new Message();

        var chat = ChatDtoMapper.fromDto(messageDto.getChat());
        var user = UserDtoMapper.fromDto(messageDto.getUser());
        chat.setId(chatId);
        user.setId(userId);
        message.setChat(chat);
        message.setUser(user);
        message.setText(messageDto.getMessage());
        message.setCreationOn(LocalDateTime.now());
        return message;
    }

    public static Message fromDto(MessageDto messageDto, Long messageId, Long userId, Long chatId) {
        var message = new Message();

        var chat = ChatDtoMapper.fromDto(messageDto.getChat());
        var user = UserDtoMapper.fromDto(messageDto.getUser());
        chat.setId(chatId);
        user.setId(userId);
        message.setId(messageId);
        message.setChat(chat);
        message.setUser(user);
        message.setText(messageDto.getMessage());
        message.setCreationOn(LocalDateTime.now());
        return message;
    }

    public static MessageDto toDto(Message message) {
        var messageDto = new MessageDto();

        messageDto.setMessage(message.getText());
        messageDto.setChat(ChatDtoMapper.toDto(message.getChat()));
        messageDto.setUser(UserDtoMapper.toDto(message.getUser()));
        messageDto.setCreationOn(message.getCreationOn());
        return messageDto;
    }
}
