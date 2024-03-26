package hw3.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class MessageDto {
    private UserDto user;
    private ChatDto chat;
    private String message;
    private LocalDateTime creationOn;

    public MessageDto(UserDto user, ChatDto chat, String message, LocalDateTime creationOn) {
        this.user = user;
        this.chat = chat;
        this.message = message;
        this.creationOn = creationOn;
    }

    public MessageDto() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public ChatDto getChat() {
        return chat;
    }

    public void setChat(ChatDto chat) {
        this.chat = chat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationOn() {
        return creationOn;
    }

    public void setCreationOn(LocalDateTime creationOn) {
        this.creationOn = creationOn;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MessageDto that = (MessageDto) object;
        return Objects.equals(user, that.user) && Objects.equals(chat, that.chat) && Objects.equals(message, that.message) && Objects.equals(creationOn, that.creationOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, chat, message, creationOn);
    }
}
