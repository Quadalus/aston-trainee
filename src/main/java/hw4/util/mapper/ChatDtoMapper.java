package hw4.util.mapper;

import hw4.dto.ChatDto;
import hw4.model.Chat;

import java.time.LocalDateTime;

public final class ChatDtoMapper {
    private ChatDtoMapper() {
    }

    public static Chat fromDto(ChatDto chatDto) {
        var chat = new Chat();

        chat.setTitle(chatDto.getTitle());
        chat.setCreatedOn(LocalDateTime.now());
        return chat;
    }

    public static Chat fromDto(ChatDto chatDto, Long id) {
        var chat = new Chat();

        chat.setId(id);
        chat.setTitle(chatDto.getTitle());
        chat.setCreatedOn(LocalDateTime.now());
        return chat;
    }

    public static ChatDto toDto(Chat user) {
        var chatDto = new ChatDto();

        chatDto.setTitle(user.getTitle());
        chatDto.setCreatedOn(user.getCreatedOn());
        return chatDto;
    }
}
