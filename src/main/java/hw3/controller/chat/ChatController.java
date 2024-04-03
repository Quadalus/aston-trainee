package hw3.controller.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw3.dto.ChatDto;
import hw3.service.Service;
import hw3.service.impl.ChatServiceImpl;
import hw3.util.ObjectMapperUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@WebServlet("/chat")
public class ChatController extends HttpServlet {
    private final Service<ChatDto> chatService = ChatServiceImpl.getInstance();
    private final ObjectMapper objectMapper = ObjectMapperUtil.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setStatus(200);
        var id = req.getParameter("id");
        var chatDto = chatService.findById(Long.parseLong(id));

        try (var writer = resp.getWriter()) {
            writer.println(objectMapper.writeValueAsString(chatDto));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var inputStream = req.getReader();
             var writer = resp.getWriter()) {
            var json = inputStream
                    .lines()
                    .collect(Collectors.joining("\n"));
            var chatDto = objectMapper.readValue(json, ChatDto.class);
            writer.println(objectMapper.writeValueAsString(chatService.add(chatDto)));

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
            var chatDto = objectMapper.readValue(json, ChatDto.class);
            writer.println(objectMapper.writeValueAsString(chatService.update(chatDto, id)));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        var id = Long.parseLong(req.getParameter("id"));
        chatService.deleteById(id);
    }
}
