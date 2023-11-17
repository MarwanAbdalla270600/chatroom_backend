import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Message {
    private String content;
    private LocalDateTime time;
    private User sender;

    public Message(String content, LocalDateTime time, User sender) {
        this.content = content;
        this.time = time;
        this.sender = sender;
    }
}
