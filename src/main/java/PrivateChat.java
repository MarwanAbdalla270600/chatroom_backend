import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@ToString
public class PrivateChat extends Chat{
    private User participant1;
    private User participant2;

    public PrivateChat(String chatID, LocalDateTime time, List<Message> messageList, User participant1, User participant2) {
        super(chatID, time, messageList);
        this.participant1 = participant1;
        this.participant2 = participant2;
    }
}
