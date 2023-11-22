package chat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import message.GroupChatMessage;
import user.User;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class GroupChat extends Chat {
    private String groupName;
    private List<User> members;
    private int maxMembers;
    private List<GroupChatMessage> messages;

    public GroupChat(int chatId, String groupName, List<User> members, int maxMembers) {
        super(chatId);
        this.groupName = groupName;
        this.members = members;
        this.maxMembers = maxMembers;
        this.messages = new LinkedList<>();
    }
}
