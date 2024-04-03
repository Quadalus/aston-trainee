package hw2.util.mapper;

import hw2.dto.UsersChatsDto;
import hw2.model.UsersChats;

public class UsersChatsDtoMapper {
    private UsersChatsDtoMapper() {
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
