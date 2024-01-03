package user;

import chat.GroupChat;
import chat.PrivateChat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import message.FriendRequestMessage;
import message.StatusMessage;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @EqualsAndHashCode.Include
    @Id                                                     //Ich glaub wir haben uns darauf geeinigt dass der Username auch gleichzeitig die ID sein soll.
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name="user_friends",
            joinColumns = @JoinColumn(name = "user_username", referencedColumnName = "username"),       //spezifiziert die Spalte für den Primärschlüssel des Users
            inverseJoinColumns = @JoinColumn(name="friend_username", referencedColumnName = "username") //spezifiziert die Spalte für den Fremdschlüssel des Freundes
    )
    private Set<User> friendList;

    @ManyToMany
    @JoinTable(
            name="user_groupChat",
            joinColumns = @JoinColumn(name="username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "groupChat_id",referencedColumnName = "chatId")
    )
    private Set<GroupChat> groupChats;

    @ManyToMany
    @JoinTable(
            name="user_privateChat",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "privateChat_id", referencedColumnName = "chatId")
    )
    private Set<PrivateChat> privateChats;

    @OneToMany(mappedBy = "receiver")
    //private ArrayList<FriendRequest> friendRequests;
    private List<FriendRequest> friendRequests = new ArrayList<>();

    public User( String username, String password) {
        this.username = username;
        this.password = password;
        this.friendList = new HashSet<>();
        this.groupChats = new HashSet<>();
        this.privateChats = new HashSet<>();
        this.friendRequests = new ArrayList<>();
    }

    public User() {

    }

    public void sendFriendRequest(User receiver) {
        if(receiver != null && !this.friendList.contains(receiver) && !hasSentRequest(receiver)) {
            FriendRequestMessage friendRequestMessage = new FriendRequestMessage(this, receiver);
            FriendRequest friendRequest = new FriendRequest(this, receiver);
            addFriendRequest(friendRequest);

            String senderMsg = "Friend request sent to " + receiver.getUsername();
            String receiverMsg = "Friend request received from " + this.getUsername();
            sendNotification(this, new StatusMessage(senderMsg));
            sendNotification(receiver, new StatusMessage(receiverMsg));
        }
    }

    public boolean hasSentRequest(User receiver) {
        for (FriendRequest request : friendRequests) {
            if (request.getReceiver().equals(receiver) && request.getStatus() == FriendRequest.FriendRequestStatus.PENDING) {
                return true;
            }
        }
        return false;
    }
    public void sendNotification(User user, StatusMessage message) {
        //user.statusChat.addStatusMessage(message); //TODO:
    }

    public void addFriendRequest(FriendRequest friendRequest) { //adds to pending friend request to sender & receiver
        User sender = friendRequest.getSender();
        User receiver = friendRequest.getReceiver();
        sender.friendRequests.add(friendRequest);
        receiver.friendRequests.add(friendRequest);
    }

    public void removeFriendRequest(FriendRequest friendRequest) {
        User sender = friendRequest.getSender();
        User receiver = friendRequest.getReceiver();
        sender.friendRequests.remove(friendRequest);
        receiver.friendRequests.remove(friendRequest);
    }

    public String notificationMsg(FriendRequest friendRequest) {
        String acceptMsg = "Friend request was accepted. " + friendRequest.getSender() + "and " + friendRequest.getReceiver() + " are now friends";
        String declineMsg = "Friend request was declined";
        String pendingMsg = "Friend request is pending";

        if(friendRequest.getStatus() == FriendRequest.FriendRequestStatus.ACCEPTED) {
            return acceptMsg;
        }
        else if (friendRequest.getStatus() == FriendRequest.FriendRequestStatus.DECLINED) {
            return declineMsg;
        }
        else {
            return pendingMsg;
        }
    }

    public void acceptFriendRequest(FriendRequest friendRequest) {
        User sender = friendRequest.getSender();
        User receiver = friendRequest.getReceiver();

        //check if status is pending AND current user is the actual receiver of this friendRequest
        if (friendRequest.getStatus() == FriendRequest.FriendRequestStatus.PENDING && this.equals(receiver)) {
            sender.friendList.add(receiver);
            receiver.friendList.add(sender);
            friendRequest.setStatus(FriendRequest.FriendRequestStatus.ACCEPTED); //sets status to Accepted

            sendNotification(sender, new StatusMessage(notificationMsg(friendRequest)));
            sendNotification(receiver, new StatusMessage(notificationMsg(friendRequest)));

            removeFriendRequest(friendRequest);

        }
        else {
            //TODO: irgend a error handling
        }
    }

    public void declineFriendRequest(FriendRequest friendRequest) {
        User sender = friendRequest.getSender();
        User receiver = friendRequest.getReceiver();
        if (friendRequest.getStatus() == FriendRequest.FriendRequestStatus.PENDING && this.equals(receiver)) {
            friendRequest.setStatus(FriendRequest.FriendRequestStatus.DECLINED); //sets status to Declined
            sendNotification(sender, new StatusMessage(notificationMsg(friendRequest)));
            sendNotification(receiver, new StatusMessage(notificationMsg(friendRequest)));
            removeFriendRequest(friendRequest);
        }
        else {
            //TODO: error handling
        }
    }

    public void removeFriend(User user) {
        //Removes a friend from both friendlists; asynchrone Freundeslisten machen keinen Sinn (wie bei FacebooK?!)
        if (this.friendList.contains(user)) {
            this.friendList.remove(user);
            user.friendList.remove(this);
        }
    }

}
