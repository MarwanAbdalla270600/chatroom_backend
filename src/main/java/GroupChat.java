import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@ToString
public class GroupChat extends Chat{
    private String groupName;
    private List<User> participants;
    private int maxParticipants;

    public GroupChat(String chatID, LocalDateTime time, List<Message> messageList, String groupName, List<User> participants, int maxParticipants) {
        super(chatID, time, messageList);
        this.groupName = groupName;
        this.participants = participants;
        this.maxParticipants = maxParticipants;
    }
}
