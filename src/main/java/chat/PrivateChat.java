package chat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import message.PrivateChatMessage;
import user.User;

import java.util.LinkedList;
import java.util.List;

@Getter

@EqualsAndHashCode(callSuper = true)
public class PrivateChat extends Chat {
    private User firstMember;
    private User secondMember;
    private List<PrivateChatMessage> messages;

    public PrivateChat(User firstMember, User secondMember) {
            super();
            this.firstMember = firstMember;
            this.secondMember = secondMember;
            this.messages = new LinkedList<>();
            firstMember.getPrivateChats().add(this);
            secondMember.getPrivateChats().add(this);
        }

    public static PrivateChat createChatIfFriends(User user1, User user2) {
        if(user1.getFriendList().contains(user2)) {
            return new PrivateChat(user1, user2);
        } else {
            throw new IllegalArgumentException("Users must be friends to send each other messages");
        }
    }

    public void sendMessage(User sender, String messageText) {
        User receiver = sender.equals(firstMember) ? secondMember : firstMember;
        PrivateChatMessage message = new PrivateChatMessage(messageText, sender, receiver);
        messages.add(message);
    }

    @Override
    public String toString() {
        return "PrivateChat{" +
                "chatId=" + getChatId() +
                ", members=[" + firstMember.getUsername() + ", " + secondMember.getUsername() + "]" +
                "}";
    }

}
