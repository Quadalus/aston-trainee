package hw3.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Chat {
    private Long id;
    private String title;
    private LocalDateTime creationOn;

    public Chat(Long id, String title, LocalDateTime creationOn) {
        this.id = id;
        this.title = title;
        this.creationOn = creationOn;
    }

    public Chat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        Chat chat = (Chat) object;
        return Objects.equals(id, chat.id) && Objects.equals(title, chat.title) && Objects.equals(creationOn, chat.creationOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, creationOn);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creationOn=" + creationOn +
                '}';
    }
}
