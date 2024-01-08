package chat;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import message.GroupChatMessage;
import message.PrivateChatMessage;
import user.User;

import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class GroupChat extends Chat<GroupChatMessage> {
    @Column(name = "group_name")
    private String groupName;

    @ManyToOne
    private User creator;

    @ManyToMany
    @JoinTable(
            name = "group_chat_members",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id") //
    ) //establish a many-to-many relationship between GroupChat and User using a join table named "group_chat_members"
    private List<User> members;

    @Column(name = "max_members")
    private int maxMembers;

    @OneToMany(mappedBy = "groupChat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupChatMessage> messages;

    public GroupChat(String groupName, User creator, List<User> members, int maxMembers) {
        super();
        this.groupName = groupName;
        this.creator = creator;
        this.members = members;
        this.maxMembers = maxMembers;
        //this.messages = new LinkedList<>();
    }

    public static GroupChat createChatIfFriends(String groupName, User creator, List<User> members, int maxMembers) {
        for (User member : members) {
            if (!creator.getFriendList().contains(member)) {
                throw new IllegalArgumentException("All users must be friends with the admin/creator to create a group chat.");
            }
        }
        return new GroupChat(groupName, creator, members, maxMembers);
    }

    public void sendMessage(User sender, String messageText) {
        GroupChatMessage message = new GroupChatMessage(messageText, sender, this); // 'this' refers to the current GroupChat instance
        messages.add(message);
    }

    @Override
    public String toString() {
        for (User member : members){
            return member.getUsername();
        }
        return "GroupChat{" +
                "chatId=" + getChatId() +
                ", time=[" + getDate() +
                "}";
    }
    public GroupChat() {

    }
}
