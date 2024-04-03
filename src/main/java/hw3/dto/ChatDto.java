package hw3.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatDto {
    private String title;
    private LocalDateTime createdOn;
}
