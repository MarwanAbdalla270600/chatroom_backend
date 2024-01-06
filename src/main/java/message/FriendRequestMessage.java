package message;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.User;
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class FriendRequestMessage extends Message {

    @ManyToOne
    User sender;
    @ManyToOne
    User receiver;
    @Column(name = "status")
    boolean pending;

    public FriendRequestMessage(User sender, User receiver) {
        super();
        this.sender = sender;
        this.receiver = receiver;
        this.pending = true;
    }

    public FriendRequestMessage() {

    }
}
