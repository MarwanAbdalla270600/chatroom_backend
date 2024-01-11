package message;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.User;
import chat.PrivateChat;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class PrivateChatMessage extends Message implements Serializable {
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

    @Override
    public String toString() {
        DateTimeFormatter timeTransformer = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //for displaying time in a readable format
        return "Message from " + sender.getUsername() +
                " to " + receiver.getUsername() +
                " at " + getTime().format(timeTransformer) +
                ": " + data;
    }


}
