import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class GroupChat extends Chat{
    private String groupName;
    private List<User> members;
    private int maxMembers;

    public GroupChat(String chatID, LocalDateTime time, String groupName, List<User> members, int maxMembers) {
        super(chatID, time, new LinkedList<>());
        this.groupName = groupName;
        this.members = members;
        this.maxMembers = maxMembers;
    }
}
