package hw4.util;

import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.graph.RootGraph;

@UtilityClass
public class EntityGraphHintUtil {

    public static RootGraph<?> getUserWithChatsHint(Session session) {
        return session.getEntityGraph("userWithChats");
    }

    public static RootGraph<?> getChatWithUsersHint(Session session) {
        return session.getEntityGraph("chatWithUsers");
    }

    public static RootGraph<?> getMessageWithUserAndChatHint(Session session) {
        return session.getEntityGraph("messageWithUserAndChat");
    }

    public static RootGraph<?> getUserMessageHint(Session session) {
        return session.getEntityGraph("userWithMessages");
    }

    public static RootGraph<?> getChatMessageHint(Session session) {
        return session.getEntityGraph("chatWithMessages");
    }
}

