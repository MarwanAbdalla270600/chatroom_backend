import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@ToString
public abstract class Chat {
    private String chatID;
    private LocalDateTime time;
    private List<Message> messageList;      //Liste an Nachrichten die verschickt wurden.... ka ob das so sinn macht?

    public Chat(String chatID, LocalDateTime time, List<Message> messageList) {
        this.chatID = chatID;
        this.time = time;
        this.messageList = messageList;
    }
}
