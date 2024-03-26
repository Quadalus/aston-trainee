package hw3.controller.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw3.dto.MessageDto;
import hw3.service.MessageService;
import hw3.service.impl.MessageServiceImpl;
import hw3.util.ObjectMapperUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@WebServlet("/message")
public class MessageController extends HttpServlet {
    private final MessageService messageService = MessageServiceImpl.getInstance();
    private final ObjectMapper objectMapper = ObjectMapperUtil.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setStatus(200);
        var id = req.getParameter("id");
        var messageDto = messageService.findById(Long.parseLong(id));

        try (var writer = resp.getWriter()) {
            writer.println(objectMapper.writeValueAsString(messageDto));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var inputStream = req.getReader();
             var writer = resp.getWriter()) {
            var json = inputStream
                    .lines()
                    .collect(Collectors.joining("\n"));

            var userId = Long.parseLong(req.getParameter("userId"));
            var chatId = Long.parseLong(req.getParameter("chatId"));

            var messageDto = objectMapper.readValue(json, MessageDto.class);
            writer.println(objectMapper.writeValueAsString(messageService.add(messageDto, userId, chatId)));

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var inputStream = req.getReader();
             var writer = resp.getWriter()) {
            var messageId = Long.parseLong(req.getParameter("messageId"));
            var userId = Long.parseLong(req.getParameter("userId"));
            var chatId = Long.parseLong(req.getParameter("chatId"));
            var json = inputStream
                    .lines()
                    .collect(Collectors.joining("\n"));
            var messageDto = objectMapper.readValue(json, MessageDto.class);
            writer.println(objectMapper.writeValueAsString(messageService.update(messageDto, messageId, userId, chatId)));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        var id = Long.parseLong(req.getParameter("id"));
        messageService.deleteById(id);
    }
}
