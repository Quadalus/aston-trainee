package hw4.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NamedEntityGraph(name = "messageWithUserAndChat",
        attributeNodes = {
        @NamedAttributeNode(value = "user"), @NamedAttributeNode(value = "chat")
})
@Entity
@Table(name = "message")
@Getter
@Setter
@ToString(exclude = {"user", "chat"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user", "chat"}, callSuper = true)
public class Message extends BaseEntity<Long> {
    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;
}
