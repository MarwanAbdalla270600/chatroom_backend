package user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendRequest {
    private User sender;
    private User receiver;
    private int status;

    private static int pending = 0;
    private static int accepted = 1;
    private static int declined = 2;

    public FriendRequest(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = pending;
    }


}
