import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Message {
    private String data;
    private LocalDateTime time;
    private User sender;

    public Message(String data, LocalDateTime time, User sender) {
        this.data = data;
        this.time = time;
        this.sender = sender;
    }
}
