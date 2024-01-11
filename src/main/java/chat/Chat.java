package chat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import message.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedList;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Chat<T extends Message> {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;

    @Column(name = "creation_date", nullable = false) //Heißt dass das Feld nicht leer sein darf
    private LocalDateTime date;
    private static int nextId = 0;
    @OneToMany(mappedBy = "chat") // Assuming 'chat' is a field in Message
    private List<Message> messages;


    public Chat() {
        this.date = LocalDateTime.now();
        //this.messages = new LinkedList<>();
        this.chatId = nextId;
        nextId++;
    }
    public List<T> getMessages() {
        return (List<T>) messages;
    }


}
