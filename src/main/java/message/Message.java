package message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;


import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Message {
    @EqualsAndHashCode.Include
    private int messageId;
    private LocalDateTime time;

    public Message(int messageId) {
        this.messageId = messageId;
        this.time = LocalDateTime.now();
    }
}
