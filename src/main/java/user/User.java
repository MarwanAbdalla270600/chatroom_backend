package user;

import chat.GroupChat;
import chat.PrivateChat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString
public class User {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
