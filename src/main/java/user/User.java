package user;

import chat.GroupChat;
import chat.PrivateChat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import message.FriendRequestMessage;
import message.Message;
import message.StatusMessage;

import java.util.ArrayList;
import java.util.HashSet;
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
    private ArrayList<FriendRequest> friendRequests;

    public User( String username, String password) {
        this.username = username;
        this.password = password;
        this.friendList = new HashSet<>();
        this.groupChats = new HashSet<>();
        this.privateChats = new HashSet<>();
        this.friendRequests = new ArrayList<>();
    }

    public void sendFriendRequest(User receiver) {
        if(receiver != null && !this.friendList.contains(receiver) && !hasSentRequest(receiver)) {
            FriendRequestMessage friendRequestMessage = new FriendRequestMessage(this, receiver);
            FriendRequest friendRequest = new FriendRequest(this, receiver);
            this.friendRequests.add(friendRequest);
            receiver.friendRequests.add(friendRequest);
            StatusMessage statusMessage = new StatusMessage("Friend request sent to " + receiver.getUsername());

        }
    }

    public boolean hasSentRequest(User receiver) {
        for (FriendRequest request : friendRequests) {
            if (request.getReceiver().equals(receiver) && request.getStatus() == 0) {
                return true;
            }
        }
        return false;
    }


}
