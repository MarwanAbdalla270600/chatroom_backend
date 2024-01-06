package chat;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import message.GroupChatMessage;
import user.User;

import java.util.LinkedList;
import java.util.List;
@Entity
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class GroupChat extends Chat<GroupChatMessage> {
    private String groupName;

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

    public GroupChat(String groupName, List<User> members, int maxMembers) {
        super();
        this.groupName = groupName;
        this.members = members;
        this.maxMembers = maxMembers;
        //this.messages = new LinkedList<>();
    }

    public GroupChat() {

    }
}
