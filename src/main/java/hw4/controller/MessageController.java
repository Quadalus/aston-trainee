package hw4.controller;

import hw4.dto.MessageDto;
import hw4.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MessageDto getUserById(@RequestParam("id") Long id) {
        return messageService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public MessageDto addUser(@RequestParam("userId") Long userId,
                              @RequestParam("chatId") Long chatId,
                              @RequestBody() MessageDto userDto) {
        return messageService.add(userDto, userId, chatId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public MessageDto updateUser(@RequestParam("messageId") Long messageId,
                                 @RequestParam("userId") Long userId,
                                 @RequestParam("chatId") Long chatId,
                              @RequestBody() MessageDto userDto) {
        return messageService.update(userDto, messageId, userId, chatId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@RequestParam("id") Long id) {
        messageService.deleteById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageDto> getUserById() {
        return messageService.findAll();
    }
}
