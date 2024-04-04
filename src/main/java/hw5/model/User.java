package hw5.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedEntityGraph(name = "userWithChats",
        attributeNodes = @NamedAttributeNode(value = "chats")
)
@NamedEntityGraph(name = "userWithMessages",
        attributeNodes = @NamedAttributeNode(value = "messages", subgraph = "messageChat"),
        subgraphs = @NamedSubgraph(name = "messageChat", attributeNodes =  @NamedAttributeNode("chat"))
)
@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(exclude = "chats", callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "chats", callSuper = true)
public class User extends BaseEntity<Long> {
    @Column(name = "name")
    private String username;

    private String email;

    @ManyToMany(mappedBy = "users")
    private Set<Chat> chats = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Message> messages = new ArrayList<>();
}

