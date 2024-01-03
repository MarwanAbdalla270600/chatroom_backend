package message;

import chat.Chat;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.User;
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class StatusMessage extends Message{
    @Column(name = "data")
    private String data;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;

    public StatusMessage(User sender, User receiver, String data) {
        super();
        this.sender = sender;
        this.receiver = receiver;
        this.data = data;
    }

    public StatusMessage(String data) {
        super();
        this.data = data;
    }

    public StatusMessage() {

    }
}
