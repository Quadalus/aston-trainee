package hw3.util.mapper;

import hw3.dto.UsersChatsDto;
import hw3.model.UsersChats;

public class UsersChatsDtoMapper {
    private UsersChatsDtoMapper() {
    }

    public static UsersChats fromDto(UsersChatsDto usersChatsDto) {
        var usersChats = new UsersChats();

        usersChats.setChats(usersChatsDto.getChats()
                .stream()
                .map(ChatDtoMapper::fromDto)
                .toList());

        usersChats.setUsers(usersChatsDto.getUsers()
                .stream()
                .map(UserDtoMapper::fromDto)
                .toList());
        return usersChats;
    }

    public static UsersChatsDto toDto(UsersChats usersChats) {
        var usersChatsDto = new UsersChatsDto();

        usersChatsDto.setChats(usersChats.getChats()
                .stream()
                .map(ChatDtoMapper::toDto)
                .toList());

        usersChatsDto.setUsers(usersChats.getUsers()
                .stream()
                .map(UserDtoMapper::toDto)
                .toList());
        return usersChatsDto;
    }
}
