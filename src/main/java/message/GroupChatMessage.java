package message;

import chat.GroupChat;
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

public class GroupChatMessage extends Message {
    @Column(name = "data", nullable = false)
    String data;

    @ManyToOne
    User sender;

    @ManyToOne
    GroupChat receiver;

    @ManyToOne
    GroupChat groupChat; //reference to GroupChat

    public GroupChatMessage(int messageId, String data, User sender, GroupChat receiver) {
        super();
        this.data = data;
        this.sender = sender;
        this.receiver = receiver;
    }

    public GroupChatMessage() {

    }
}
