package chat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.User;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PrivateChat extends Chat {
    private User firstMember;
    private User secondMember;

    public PrivateChat(int chatId,  User firstMember, User secondMember) {
        super(chatId);
        this.firstMember = firstMember;
        this.secondMember = secondMember;
    }
}
