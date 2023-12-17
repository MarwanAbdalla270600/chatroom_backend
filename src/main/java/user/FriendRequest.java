package user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendRequest {
    private User sender;
    private User receiver;
    private FriendRequestStatus status;

    enum FriendRequestStatus {
        PENDING,
        ACCEPTED,
        DECLINED
    }

    public FriendRequest(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = FriendRequestStatus.PENDING;
    }

}
