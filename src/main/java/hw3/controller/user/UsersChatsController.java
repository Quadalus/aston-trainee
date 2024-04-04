package hw3.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw3.service.UsersChatsService;
import hw3.service.impl.UsersChatsServiceImpl;
import hw3.util.ObjectMapperUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/users/chats/all")
public class UsersChatsController extends HttpServlet {
    private final UsersChatsService userService = UsersChatsServiceImpl.getInstance();
    private final ObjectMapper objectMapper = ObjectMapperUtil.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setStatus(200);
        var userDto = userService.findAll();

        try (var writer = resp.getWriter()) {
            writer.println(objectMapper.writeValueAsString(userDto));
        }
    }
}
