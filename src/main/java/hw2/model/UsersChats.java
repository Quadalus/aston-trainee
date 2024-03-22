package hw2.model;

import java.util.List;
import java.util.Objects;

public class UsersChats {
    private List<User> users;
    private List<Chat> chats;

    public UsersChats(List<User> users, List<Chat> chats) {
        this.users = users;
        this.chats = chats;
    }

    public UsersChats() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UsersChats that = (UsersChats) object;
        return Objects.equals(users, that.users) && Objects.equals(chats, that.chats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, chats);
    }

    @Override
    public String toString() {
        return "UsersChats{" +
                "users=" + users +
                ", chats=" + chats +
                '}';
    }
}
