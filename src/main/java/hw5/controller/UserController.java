package hw5.controller;

import hw5.dto.MessageDto;
import hw5.dto.UserDto;
import hw5.dto.UsersChatsDto;
import hw5.service.CommonService;
import hw5.service.MessageService;
import hw5.service.UsersChatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name="Пользователь", description="Получение пользователя, его сообщений и его чаты")
public class UserController {
    private final CommonService<UserDto> userServiceImpl;
    private final MessageService messageService;
    private final UsersChatsService usersChatsServiceImpl;

    @Operation(
            summary = "Получения пользователя",
            description = "Позволяет получить пользователя по id"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@Parameter(description = "Идентификатор пользователя") @RequestParam("id") Long id) {
        return userServiceImpl.findById(id);
    }

    @Operation(
            summary = "Регистрация пользователя",
            description = "Позволяет зарегистрировать пользователя"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto addUser(@RequestBody() UserDto userDto) {
        return userServiceImpl.add(userDto);
    }

    @Operation(
            summary = "Обновление пользователя",
            description = "Позволяет обновить пользователя"
    )
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@Parameter(description = "Идентификатор пользователя") @RequestParam("id") Long id,
                              @RequestBody() UserDto userDto) {
        return userServiceImpl.update(userDto, id);
    }

    @Operation(
            summary = "Удаление пользователя",
            description = "Позволяет удалить пользователя по id"
    )
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@Parameter(description = "Идентификатор пользователя") @RequestParam("id") Long id) {
        userServiceImpl.deleteById(id);
    }

    @Operation(
            summary = "Получения пользователей",
            description = "Позволяет всех пользователей"
    )
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUser() {
        return userServiceImpl.findAll();
    }

    @Operation(
            summary = "Получения сообщений пользователя",
            description = "Позволяет получить все сообщения отправленые пользователем"
    )
    @GetMapping("/message")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageDto> getUserMessage(@Parameter(description = "Идентификатор пользователя") @RequestParam("userId") Long userId) {
        return messageService.findUserMessageById(userId);
    }

    @Operation(
            summary = "Получения чатов пользователя",
            description = "Позволяет получить все чаты пользователя"
    )
    @GetMapping("/chat")
    @ResponseStatus(HttpStatus.OK)
    public UsersChatsDto getUserChat(@Parameter(description = "Идентификатор пользователя") @RequestParam("userId") Long userId) {
        return usersChatsServiceImpl.findUserChatsById(userId);
    }
}
