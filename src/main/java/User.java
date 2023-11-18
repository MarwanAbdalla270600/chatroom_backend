import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    private String username;
    private String password;
    private List<User> friendList;
    public static Set<User> userList = new HashSet<>();

    public User( String username, String password) {
        this.username = username;
        this.password = password;
        this.friendList = new LinkedList<>();
        userList.add(this);
    }


    public static void printUserList() {
        System.out.println(userList);
    }

    public static boolean deleteUser(User user) {
        if(userList.contains(user)) {
            userList.remove(user);
            return true;
        }
        return false;
    }

}
