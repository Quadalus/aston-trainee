package hw3.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Long id;
    private String text;
    private User user;
    private Chat chat;
    private LocalDateTime creationOn;

    public Message(Long id, String text, User user, Chat chat, LocalDateTime creationOn) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.chat = chat;
        this.creationOn = creationOn;
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
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
        Message message = (Message) object;
        return Objects.equals(id, message.id) && Objects.equals(text, message.text) && Objects.equals(user, message.user) && Objects.equals(chat, message.chat) && Objects.equals(creationOn, message.creationOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, user, chat, creationOn);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", chat=" + chat +
                ", creationOn=" + creationOn +
                '}';
    }
}
