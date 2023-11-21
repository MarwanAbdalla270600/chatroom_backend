package message;

import chat.Chat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class StatusMessage extends Message{
    private String data;
    private Chat chat;

    public StatusMessage(int messageId, String data, Chat chat) {
        super(messageId);
        this.data = data;
        this.chat = chat;
    }
}
