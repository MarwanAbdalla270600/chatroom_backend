package chat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import message.PrivateChatMessage;
import user.User;

import java.util.LinkedList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PrivateChat extends Chat {
    private User firstMember;
    private User secondMember;
    private List<PrivateChatMessage> messages;

    public PrivateChat(User firstMember, User secondMember) {
        super();
        this.firstMember = firstMember;
        this.secondMember = secondMember;
        this.messages = new LinkedList<>();
    }
}
