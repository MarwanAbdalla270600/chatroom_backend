package user;

import chat.GroupChat;
import chat.PrivateChat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import message.FriendRequestMessage;
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

    public void setUsername(String username) {
        if (isValidUsername(username)) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Invalid username " +
                    "(Usernames must be min. 3 but max 25 char of length " +
                    "and only letters and digits");
        }
    }

    private boolean isValidUsername(String username) {
        if(username == null) return false;

        int minLength = 3;
        int maxLength = 25;
        if (username.length() < minLength || username.length() > maxLength) {
            return false;
        }

        char[] lettersUsername = username.toCharArray();
        for (char letter : lettersUsername) {
            if(!Character.isLetterOrDigit(letter)) return false;
        }
        return true;
    }

    //TODO: implement a isUniqueUsername with Database query (username list)

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
