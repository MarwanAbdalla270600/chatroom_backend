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

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
        this.friendList = new HashSet<>();
        this.groupChats = new HashSet<>();
        this.privateChats = new HashSet<>();
        this.friendRequests = new ArrayList<>();
    }

    public User() {

    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' + '}';
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
        for (char character : lettersUsername) {
            if(!Character.isLetterOrDigit(character)) return false;
        }
        return true;
    }

    //TODO: implement a isUniqueUsername with Database query (username list)

    public void setPassword(String password) {
        if (isValidPassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password" +
                    "(Passwords must be at least 6 char long, and must " +
                    "contain BOTH digits and letters");
        }
    }
    private boolean isValidPassword(String password) {
        if(password == null) return false;

        int minLength = 6;
        int maxLength = 25;
        if(password.length() < minLength || password.length() > maxLength) {
            return false;
        }

        boolean containsDigit = false;
        boolean containsLetter = false;
        char[] lettersPassword = password.toCharArray();

        for (char character : lettersPassword) {
            if(Character.isDigit(character)) {
                containsDigit = true;
            }
            else if(Character.isLetter(character)) {
                containsLetter = true;
            }
        }
        return containsDigit && containsLetter;
    }

    public void sendFriendRequest(User receiver) {
        if(receiver != null && !this.friendList.contains(receiver) && !hasPendingRequestsWith(receiver)) {
            FriendRequestMessage friendRequestMessage = new FriendRequestMessage(this, receiver);
            FriendRequest friendRequest = new FriendRequest(this, receiver);
            addFriendRequest(friendRequest);

            String senderMsg = "Friend request sent to " + receiver.getUsername();
            String receiverMsg = "Friend request received from " + this.getUsername();
            sendNotification(this, new StatusMessage(senderMsg));
            sendNotification(receiver, new StatusMessage(receiverMsg));
        }
    }

    public boolean hasPendingRequestsWith(User otherUser) {
        for (FriendRequest request : this.friendRequests) {
            if (request.getReceiver().equals(otherUser) && request.getStatus() == FriendRequest.FriendRequestStatus.PENDING) {
                return true;
            }
        }
        //checking if one request between two users already exists (in both directions)
        for (FriendRequest request : otherUser.getFriendRequests()) {
            if (request.getReceiver().equals(this) && request.getStatus() == FriendRequest.FriendRequestStatus.PENDING) {
                return true;
            }
        }
        return false;
    }
    public void sendNotification(User user, StatusMessage message) {
        System.out.println("Notification for " + user.getUsername() + ": " + message.getData());
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

    public boolean acceptFriendRequest(FriendRequest friendRequest) {
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
            return true;
        } else {
            return false;
        }
    }

    public boolean declineFriendRequest(FriendRequest friendRequest) {
        User sender = friendRequest.getSender();
        User receiver = friendRequest.getReceiver();
        if (friendRequest.getStatus() == FriendRequest.FriendRequestStatus.PENDING && this.equals(receiver)) {
            friendRequest.setStatus(FriendRequest.FriendRequestStatus.DECLINED); //sets status to Declined
            sendNotification(sender, new StatusMessage(notificationMsg(friendRequest)));
            sendNotification(receiver, new StatusMessage(notificationMsg(friendRequest)));
            removeFriendRequest(friendRequest);
            return true;
        } else {
            return false;
        }
    }

    public void removeFriend(User user) {
        //Removes a friend from both friendlists; asynchrone Freundeslisten machen keinen Sinn (wie bei FacebooK?!)
        if (this.friendList.contains(user)) {
            this.friendList.remove(user);
            user.friendList.remove(this);
        }
    }

    public User findFriendFromFriendlist(String username) { //bin unsicher ob wir die methode überhaupt im GUI brauchen
        if (username == null || username.isEmpty()) {
            return null;
        }

        for (User friend : friendList) {
            if (friend.getUsername().equalsIgnoreCase(username)) {
                return friend;
            }
        }

        return null;
    }

    //TODO: implement findFriendFromSystem um neue Freunde zu adden

}
