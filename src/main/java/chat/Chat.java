package chat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import message.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedList;


@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Chat<T extends Message> {
    @EqualsAndHashCode.Include
    private int chatId;

    private LocalDateTime date;
    private static int nextId = 0;
    private List<T> messages;

    public Chat() {
        this.chatId = nextId++;
        this.date = LocalDateTime.now();
        this.messages = new LinkedList<>();
    }
    public List<T> getMessages() {
        return messages;
    }
}
