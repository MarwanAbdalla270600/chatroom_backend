package user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendRequest {
    private User sender;
    private User receiver;
    private int status;

    private static final int pending = 0;
    private static final int accepted = 1;
    private static final int declined = 2;

    public FriendRequest(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = pending;
    }

}
