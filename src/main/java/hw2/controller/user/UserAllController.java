package hw2.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw2.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/user/all")
public class UserAllController extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

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
