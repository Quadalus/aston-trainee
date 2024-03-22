package hw2.util.mapper;

import hw2.dto.ChatDto;
import hw2.model.Chat;

public final class ChatDtoMapper {
    private ChatDtoMapper() {
    }

    public static Chat fromDto(ChatDto chatDto) {
        var chat = new Chat();

        chat.setTitle(chatDto.getTitle());
        chat.setCreationOn(chatDto.getCretaionOn());
        return chat;
    }

    public static Chat fromDto(ChatDto chatDto, Long id) {
        var chat = new Chat();

        chat.setId(id);
        chat.setTitle(chatDto.getTitle());
        chat.setCreationOn(chatDto.getCretaionOn());
        return chat;
    }

    public static ChatDto toDto(Chat user) {
        var chatDto = new ChatDto();

        chatDto.setTitle(user.getTitle());
        chatDto.setCretaionOn(user.getCreationOn());
        return chatDto;
    }
}
