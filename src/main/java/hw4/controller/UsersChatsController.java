package hw4.controller;

import hw4.service.UsersChatsService;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/chats/all")
@RequiredArgsConstructor
public class UsersChatsController extends HttpServlet {
    private final UsersChatsService userService;

    @GetMapping
    protected String getAllUsersAndChats(Model model){
        var usersChats = userService.findAll();
        model.addAttribute("usersChats", usersChats);
        return "users-chats/find";
    }
}
