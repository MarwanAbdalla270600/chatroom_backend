package chat;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import message.PrivateChatMessage;
import user.User;

import java.util.LinkedList;
import java.util.List;
@Entity
@Getter

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
    @Override
    public String toString() {
        return "PrivateChat{" +
                "chatId=" + getChatId() +
                ", members=[" + firstMember.getUsername() + ", " + secondMember.getUsername() + "]" +
                ", time=[" + getDate() +
                ", messages[" + getMessages() +
                "}";
    }

}
