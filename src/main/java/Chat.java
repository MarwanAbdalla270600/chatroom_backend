import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedList;
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class Chat {
    private String chatID;
    private LocalDateTime datetime;         //Erstelldatum des Chats
    private List<Message> messageList;      //Liste an Nachrichten die verschickt wurden.... ka ob das so sinn macht?

    public Chat(String chatID, LocalDateTime time, List<Message> messageList) {
        this.chatID = chatID;
        this.datetime = time;
        this.messageList = new LinkedList<>();
    }
}
