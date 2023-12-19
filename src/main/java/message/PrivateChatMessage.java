package message;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.User;
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PrivateChatMessage extends Message {
    private String data;
    private User sender;
    private User receiver;

    public PrivateChatMessage(int messageId, String data, User sender, User receiver) {
        super();
        this.data = data;
        this.sender = sender;
        this.receiver = receiver;
    }
}
