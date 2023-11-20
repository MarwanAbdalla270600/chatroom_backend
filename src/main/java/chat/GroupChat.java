package chat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.User;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class GroupChat extends Chat {
    private String groupName;
    private List<User> members;
    private int maxMembers;

    public GroupChat(int chatId, String groupName, List<User> members, int maxMembers) {
        super(chatId);
        this.groupName = groupName;
        this.members = members;
        this.maxMembers = maxMembers;
    }
}
