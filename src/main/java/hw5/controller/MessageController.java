package hw5.controller;

import hw5.dto.MessageDto;
import hw5.service.MessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
@Tag(name="Сообщения", description="Получение сообщений и его пользователя")
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MessageDto getMessageById(@RequestParam("id") Long id) {
        return messageService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public MessageDto addMessage(@RequestParam("userId") Long userId,
                              @RequestParam("chatId") Long chatId,
                              @RequestBody() MessageDto userDto) {
        return messageService.add(userDto, userId, chatId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public MessageDto updateMessage(@RequestParam("messageId") Long messageId,
                                 @RequestParam("userId") Long userId,
                                 @RequestParam("chatId") Long chatId,
                              @RequestBody() MessageDto userDto) {
        return messageService.update(userDto, messageId, userId, chatId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteMessage(@RequestParam("id") Long id) {
        messageService.deleteById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageDto> getAllMessages() {
        return messageService.findAll();
    }
}
