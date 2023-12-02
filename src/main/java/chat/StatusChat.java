package chat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class StatusChat extends Chat {
    public StatusChat(int chatId) {
        super(chatId);
    }
}
