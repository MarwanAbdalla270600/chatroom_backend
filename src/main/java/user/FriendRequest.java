package user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    @Enumerated
    private FriendRequestStatus status;

    public FriendRequest() {

    }

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
