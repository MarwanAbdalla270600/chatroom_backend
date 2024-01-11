package chat;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import message.PrivateChatMessage;
import user.User;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter

@EqualsAndHashCode(callSuper = true)
public class PrivateChat extends Chat implements Serializable {
    //@ManyToOne
    private String firstMember;
    //@ManyToOne
    private String secondMember;
    @OneToMany(mappedBy = "privateChat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PrivateChatMessage> messages;

    public PrivateChat(String firstMember, String secondMember) {
            super();
            this.firstMember = firstMember;
            this.secondMember = secondMember;
            this.messages = new LinkedList<>();
        }

    public PrivateChat() {

    }

    public void addMsgToPrivateChat(PrivateChatMessage message) {
        this.messages.add(message);
    }

    @Override
    public String toString() {
        return "PrivateChat{" +
                "chatId=" + getChatId() +
                ", members=[" + firstMember + ", " + secondMember+ "]" +
                ", time=[" + getDate() +
                ", messages[" + getMessages() +
                "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivateChat that = (PrivateChat) o;
        return (Objects.equals(firstMember, that.firstMember) && Objects.equals(secondMember, that.secondMember)) ||
                (Objects.equals(firstMember, that.secondMember) && Objects.equals(secondMember, that.firstMember));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstMember, secondMember) + Objects.hash(secondMember, firstMember);
    }

}
