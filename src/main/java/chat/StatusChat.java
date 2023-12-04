package chat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import message.GroupChatMessage;
import message.StatusMessage;

import java.util.LinkedList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class StatusChat extends Chat {
    private List<StatusMessage> messages;
    public StatusChat() {
        super();
        this.messages = new LinkedList<>();
    }

    public void addStatusMessage(StatusMessage statusMessage) {
        this.messages.add(statusMessage);
    }
}
