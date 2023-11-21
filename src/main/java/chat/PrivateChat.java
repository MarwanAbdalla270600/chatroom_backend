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
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PrivateChat extends Chat {
    private User firstMember;
    private User secondMember;
    private List<PrivateChatMessage> messages;

    public PrivateChat(int chatId,  User firstMember, User secondMember) {
        super(chatId);
        this.firstMember = firstMember;
        this.secondMember = secondMember;
        this.messages = new LinkedList<>();
    }
}
