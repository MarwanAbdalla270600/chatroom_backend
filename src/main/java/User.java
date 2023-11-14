import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private List<User> friendlist;

    public User(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.friendlist = new LinkedList<>();
    }

}
