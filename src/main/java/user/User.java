package user;

import chat.GroupChat;
import chat.PrivateChat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @EqualsAndHashCode.Include
    private String username;

    private String password;
    private Set<User> friendList;
    private Set<GroupChat> groupChats;
    private Set<PrivateChat> privateChats;

    public User( String username, String password) {
        this.username = username;
        this.password = password;
        this.friendList = new HashSet<>();
        this.groupChats = new HashSet<>();
        this.privateChats = new HashSet<>();
    }

}
