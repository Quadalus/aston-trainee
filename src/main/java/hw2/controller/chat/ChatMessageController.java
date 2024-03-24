package hw2.controller.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw2.service.MessageService;
import hw2.service.impl.MessageServiceImpl;
import hw2.util.ObjectMapperUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/chat/message")
public class ChatMessageController extends HttpServlet {
    private final MessageService messageService = MessageServiceImpl.getInstance();
    private final ObjectMapper objectMapper = ObjectMapperUtil.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setStatus(200);
        var id = Long.parseLong(req.getParameter("chatId"));
        var chatMessageById = messageService.findChatMessageById(id);

        try (var writer = resp.getWriter()) {
            writer.println(objectMapper.writeValueAsString(chatMessageById));
        }
    }
}
