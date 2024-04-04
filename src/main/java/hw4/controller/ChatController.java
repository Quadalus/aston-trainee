package hw4.controller;

import hw4.dto.ChatDto;
import hw4.dto.MessageDto;
import hw4.dto.UsersChatsDto;
import hw4.service.CommonService;
import hw4.service.MessageService;
import hw4.service.UsersChatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final CommonService<ChatDto> chatServiceImpl;
    private final MessageService messageService;
    private final UsersChatsService usersChatsServiceImpl;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ChatDto getUserById(@RequestParam("id") Long id) {
        return chatServiceImpl.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ChatDto addUser(@RequestBody() ChatDto chatDto) {
        return chatServiceImpl.add(chatDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ChatDto updateUser(@RequestParam("id") Long id,
                              @RequestBody() ChatDto chatDto) {
        return chatServiceImpl.update(chatDto, id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@RequestParam("id") Long id) {
        chatServiceImpl.deleteById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ChatDto> getUserById() {
        return chatServiceImpl.findAll();
    }

    @GetMapping("/message")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageDto> getChatMessage(@RequestParam("chatId") Long chatId) {
        return messageService.findChatMessageById(chatId);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public UsersChatsDto getChatUsers(@RequestParam("chatId") Long chatId) {
        return usersChatsServiceImpl.findChatUsersById(chatId);
    }
}
