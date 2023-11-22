package message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
public abstract class Message {
    private int messageId;
    private LocalDateTime time;

    public Message(int messageId) {
        this.messageId = messageId;
        this.time = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId == message.messageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId);
    }
}
