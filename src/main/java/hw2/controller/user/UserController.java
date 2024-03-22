package hw2.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw2.dto.UserDto;
import hw2.service.impl.UserService;
import hw2.util.ObjectMapperUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private final ObjectMapper objectMapper = ObjectMapperUtil.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setStatus(200);
        var id = req.getParameter("id");
        var userDto = userService.findById(Long.parseLong(id));

        try (var writer = resp.getWriter()) {
            writer.println(objectMapper.writeValueAsString(userDto));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var inputStream = req.getReader();
             var writer = resp.getWriter()) {
            var json = inputStream
                    .lines()
                    .collect(Collectors.joining("\n"));
            var userDto = objectMapper.readValue(json, UserDto.class);
            writer.println(objectMapper.writeValueAsString(userService.add(userDto)));

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var inputStream = req.getReader();
             var writer = resp.getWriter()) {
            var id = Long.parseLong(req.getParameter("id"));
            var json = inputStream
                    .lines()
                    .collect(Collectors.joining("\n"));
            var userDto = objectMapper.readValue(json, UserDto.class);
            writer.println(objectMapper.writeValueAsString(userService.update(userDto, id)));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        var id = Long.parseLong(req.getParameter("id"));
        userService.deleteById(id);
    }
}
