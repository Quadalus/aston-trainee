package hw5.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedEntityGraph(name = "chatWithUsers",
        attributeNodes = @NamedAttributeNode(value = "users")
)
@NamedEntityGraph(name = "chatWithMessages",
        attributeNodes = @NamedAttributeNode(value = "messages", subgraph = "messageUser"),
        subgraphs = @NamedSubgraph(name = "messageUser", attributeNodes =  @NamedAttributeNode("user"))
)
@Entity
@Table(name = "chats")
@Getter
@Setter
@ToString(exclude = "users", callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "users", callSuper = true)
public class Chat extends BaseEntity<Long> {
    private String title;

    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @ManyToMany
    @JoinTable(name = "users_chats",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "chat")
    private List<Message> messages = new ArrayList<>();
}
