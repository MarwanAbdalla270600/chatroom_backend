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

    public StatusMessage(String data, Chat chat) {
        super();
        this.data = data;
        this.chat = chat;
    }

    public StatusMessage(String data) {
        super();
        this.data = data;
    }
}
