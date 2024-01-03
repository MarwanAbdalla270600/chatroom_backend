package chat;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import message.PrivateChatMessage;
import user.User;

import java.util.LinkedList;
import java.util.List;
@Entity
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PrivateChat extends Chat {
    @ManyToOne
    private User firstMember;
    @ManyToOne
    private User secondMember;
    @OneToMany(mappedBy = "privateChat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PrivateChatMessage> messages;

    public PrivateChat(User firstMember, User secondMember) {
        super();
        this.firstMember = firstMember;
        this.secondMember = secondMember;
        this.messages = new LinkedList<>();
    }

    public PrivateChat() {

    }

    public void sendMsg(User sender, String messageText) {
        User receiver = sender.equals(firstMember) ? secondMember : firstMember;
        PrivateChatMessage message = new PrivateChatMessage(messageText, sender, receiver);
        messages.add(message);
    }

}
