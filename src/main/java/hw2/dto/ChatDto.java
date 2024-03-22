package hw2.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class ChatDto {
    private String title;
    private LocalDateTime creationOn;

    public ChatDto(String title, LocalDateTime creationOn) {
        this.title = title;
        this.creationOn = creationOn;
    }

    public ChatDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCretaionOn() {
        return creationOn;
    }

    public void setCretaionOn(LocalDateTime creationOn) {
        this.creationOn = creationOn;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ChatDto chatDto = (ChatDto) object;
        return Objects.equals(title, chatDto.title) && Objects.equals(creationOn, chatDto.creationOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, creationOn);
    }

    @Override
    public String toString() {
        return "ChatDto{" +
                "title='" + title + '\'' +
                ", cretaionOn=" + creationOn +
                '}';
    }
}
