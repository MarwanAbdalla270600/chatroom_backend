package message;

import chat.Chat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.User;


import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Message {
    private String data;
    private LocalDateTime time;
    private User sender;
    private Chat receiver;

    public Message(String data, User sender, Chat receiver) {
        this.data = data;
        this.time = LocalDateTime.now();
        this.sender = sender;
        this.receiver = receiver;
    }
}
