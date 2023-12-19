package message;

import chat.Chat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.User;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class StatusMessage extends Message{
    private String data;
    private User sender;
    private User receiver;

    public StatusMessage(User sender, User receiver, String data) {
        super();
        this.sender = sender;
        this.receiver = receiver;
        this.data = data;
    }

    public StatusMessage(String data) {
        super();
        this.data = data;
    }
}
