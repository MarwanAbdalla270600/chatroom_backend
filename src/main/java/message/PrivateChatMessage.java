package message;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.User;
import chat.PrivateChat;
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PrivateChatMessage extends Message {
    @Column(name = "data", nullable = false)
    private String data;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    @ManyToOne
    private PrivateChat privateChat;  // Reference back to PrivateChat
    public PrivateChatMessage(String data, User sender, User receiver) {
        super();
        this.data = data;
        this.sender = sender;
        this.receiver = receiver;
    }

    public PrivateChatMessage() {

    }
}
