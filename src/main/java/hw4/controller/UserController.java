package hw4.controller;

import hw4.dto.MessageDto;
import hw4.dto.UserDto;
import hw4.dto.UsersChatsDto;
import hw4.service.CommonService;
import hw4.service.MessageService;
import hw4.service.UsersChatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final CommonService<UserDto> userServiceImpl;
    private final MessageService messageService;
    private final UsersChatsService usersChatsServiceImpl;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@RequestParam("id") Long id) {
        return userServiceImpl.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto addUser(@RequestBody() UserDto userDto) {
        return userServiceImpl.add(userDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@RequestParam("id") Long id,
                              @RequestBody() UserDto userDto) {
        return userServiceImpl.update(userDto, id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@RequestParam("id") Long id) {
        userServiceImpl.deleteById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUserById() {
        return userServiceImpl.findAll();
    }

    @GetMapping("/message")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageDto> getUserMessage(@RequestParam("userId") Long userId) {
        return messageService.findUserMessageById(userId);
    }

    @GetMapping("/chat")
    @ResponseStatus(HttpStatus.OK)
    public UsersChatsDto getUserChat(@RequestParam("userId") Long userId) {
        return usersChatsServiceImpl.findUserChatsById(userId);
    }
}
