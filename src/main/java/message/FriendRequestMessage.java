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
public class FriendRequestMessage extends Message {
    User sender;
    User receiver;
    boolean pending;

    public FriendRequestMessage(User sender, User receiver) {
        super();
        this.sender = sender;
        this.receiver = receiver;
        this.pending = true;
    }
}
