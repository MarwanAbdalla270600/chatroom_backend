package user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    @Override
    public String toString() {
        return "FriendRequest from " + sender.getUsername() + " to " + receiver.getUsername() + " - Status: " + status;
    }

}
