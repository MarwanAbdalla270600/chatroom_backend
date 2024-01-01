package message;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;


import java.time.LocalDateTime;
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Message {
    @EqualsAndHashCode.Include
    private int messageId;

    private static int nextId = 0;
    private LocalDateTime time;

    public Message() {
        this.messageId = nextId++;
        this.time = LocalDateTime.now();
    }
}
