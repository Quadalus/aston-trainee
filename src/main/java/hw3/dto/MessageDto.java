package hw3.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private UserDto user;
    private ChatDto chat;
    private String message;
    private LocalDateTime createdOn;
}
