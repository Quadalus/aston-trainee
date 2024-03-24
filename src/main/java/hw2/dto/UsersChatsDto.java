package hw2.dto;

import java.util.List;
import java.util.Objects;

public class UsersChatsDto {
    private List<UserDto> users;
    private List<ChatDto> chats;

    public UsersChatsDto(List<UserDto> users, List<ChatDto> chats) {
        this.users = users;
        this.chats = chats;
    }

    public UsersChatsDto() {
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public List<ChatDto> getChats() {
        return chats;
    }

    public void setChats(List<ChatDto> chats) {
        this.chats = chats;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UsersChatsDto that = (UsersChatsDto) object;
        return Objects.equals(users, that.users) && Objects.equals(chats, that.chats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, chats);
    }
}
