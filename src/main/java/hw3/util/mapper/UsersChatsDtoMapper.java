package hw3.util.mapper;

import hw3.dto.UsersChatsDto;
import hw3.model.Chat;
import hw3.model.User;

import java.util.List;

public class UsersChatsDtoMapper {
    private UsersChatsDtoMapper() {
    }

    public static UsersChatsDto toDtoUser(List<User> usersChats) {
        var usersChatsDto = new UsersChatsDto();

        var chats = getChats(usersChats);

        usersChatsDto.setChats(chats
                .stream()
                .map(ChatDtoMapper::toDto)
                .toList());

        usersChatsDto.setUsers(usersChats
                .stream()
                .map(UserDtoMapper::toDto)
                .toList());
        return usersChatsDto;
    }

    public static UsersChatsDto toDtoChat(List<Chat> usersChats) {
        var usersChatsDto = new UsersChatsDto();

        var users = getUsers(usersChats);

        usersChatsDto.setChats(usersChats
                .stream()
                .map(ChatDtoMapper::toDto)
                .toList());

        usersChatsDto.setUsers(users
                .stream()
                .map(UserDtoMapper::toDto)
                .toList());
        return usersChatsDto;
    }

    private static List<Chat> getChats(List<User> usersChats) {
        return usersChats.stream()
                .flatMap(user -> user.getChats().stream())
                .toList();
    }

    private static List<User> getUsers(List<Chat> usersChats) {
        return usersChats.stream()
                .flatMap(user -> user.getUsers().stream())
                .toList();
    }
}
