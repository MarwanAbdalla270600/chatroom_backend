package message;

import chat.GroupChat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.User;
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)

public class GroupChatMessage extends Message {
    String data;
    User sender;
    GroupChat receiver;

    public GroupChatMessage(int messageId, String data, User sender, GroupChat receiver) {
        super();
        this.data = data;
        this.sender = sender;
        this.receiver = receiver;
    }

}
