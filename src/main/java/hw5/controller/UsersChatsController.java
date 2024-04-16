package hw5.controller;

import hw5.service.UsersChatsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/chats/all")
@RequiredArgsConstructor
@Tag(name="Пользователи и чаты", description="Получение всех пользователей и чатов")
public class UsersChatsController extends HttpServlet {
    private final UsersChatsService userService;

    @GetMapping
    protected String getAllUsersAndChats(Model model){
        var usersChats = userService.findAll();
        model.addAttribute("usersChats", usersChats);
        return "users-chats/find";
    }
}
