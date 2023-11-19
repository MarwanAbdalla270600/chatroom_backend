import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PrivateChat extends Chat{
    private User firstMember;
    private User secondMember;

    public PrivateChat(String chatID, LocalDateTime time, User firstMember, User secondMember) {
        super(chatID, time, new LinkedList<>());
        this.firstMember = firstMember;
        this.secondMember = secondMember;
    }
}
