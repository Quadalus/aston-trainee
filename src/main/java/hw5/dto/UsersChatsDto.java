package hw5.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsersChatsDto {
    private List<UserDto> users;
    private List<ChatDto> chats;
}
