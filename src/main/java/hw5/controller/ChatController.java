package hw5.controller;

import hw5.dto.ChatDto;
import hw5.dto.MessageDto;
import hw5.dto.UsersChatsDto;
import hw5.service.CommonService;
import hw5.service.MessageService;
import hw5.service.UsersChatsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("/chat")
@RequiredArgsConstructor
@Tag(name="Чат", description="Получение чата, его пользователей и его сообщений")
public class ChatController {
    private final CommonService<ChatDto> chatServiceImpl;
    private final MessageService messageService;
    private final UsersChatsService usersChatsServiceImpl;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ChatDto getChatById(@RequestParam("id") Long id) {
        return chatServiceImpl.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ChatDto addChat(@RequestBody() ChatDto chatDto) {
        return chatServiceImpl.add(chatDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ChatDto updateChat(@RequestParam("id") Long id,
                              @RequestBody() ChatDto chatDto) {
        return chatServiceImpl.update(chatDto, id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteChatById(@RequestParam("id") Long id) {
        chatServiceImpl.deleteById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ChatDto> getAllChat() {
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
