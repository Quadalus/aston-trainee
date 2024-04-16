package hw5.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Schema(description = "Сущность пользователя")
public class UserDto {
    @Schema(description = "Имя пользователя", example = "Егор")
    private String username;

    @Schema(description = "Email пользователя", example = "egor@google.com")
    @Email
    private String email;
}
