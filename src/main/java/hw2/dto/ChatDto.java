package hw2.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class ChatDto {
    private String title;
    private LocalDateTime cretaionOn;

    public ChatDto(String title, LocalDateTime cretaionOn) {
        this.title = title;
        this.cretaionOn = cretaionOn;
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
        return cretaionOn;
    }

    public void setCretaionOn(LocalDateTime cretaionOn) {
        this.cretaionOn = cretaionOn;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ChatDto chatDto = (ChatDto) object;
        return Objects.equals(title, chatDto.title) && Objects.equals(cretaionOn, chatDto.cretaionOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, cretaionOn);
    }

    @Override
    public String toString() {
        return "ChatDto{" +
                "title='" + title + '\'' +
                ", cretaionOn=" + cretaionOn +
                '}';
    }
}
