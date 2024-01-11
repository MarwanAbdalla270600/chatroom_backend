package user;

import chat.GroupChat;
import chat.PrivateChat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import message.FriendRequestMessage;
import message.PrivateChatMessage;
import message.StatusMessage;
import jakarta.persistence.*;
import org.w3c.dom.ls.LSOutput;
import service.UserService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    //Ich glaub wir haben uns darauf geeinigt dass der Username auch gleichzeitig die ID sein soll.
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private char gender;

    @ManyToMany
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_username", referencedColumnName = "username"),       //spezifiziert die Spalte für den Primärschlüssel des Users
            inverseJoinColumns = @JoinColumn(name = "friend_username", referencedColumnName = "username") //spezifiziert die Spalte für den Fremdschlüssel des Freundes
    )
    private Set<User> friendList;

    @ManyToMany
    @JoinTable(
            name = "user_groupChat",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "groupChat_id", referencedColumnName = "chatId")
    )
    private Set<GroupChat> groupChats;

    @ManyToMany
    @JoinTable(
            name = "user_privateChat",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "privateChat_id", referencedColumnName = "chatId")
    )
    private Set<PrivateChat> privateChats;

    @OneToMany(mappedBy = "receiver")
    //private ArrayList<FriendRequest> friendRequests;
    private List<FriendRequest> friendRequests = new ArrayList<>();

    public User(String username, String password, char gender) {
        setUsername(username);
        setPassword(password);
        this.gender = gender;
        this.friendList = new HashSet<>();
        this.groupChats = new HashSet<>();
        this.privateChats = new HashSet<>();
        this.friendRequests = new ArrayList<>();
    }

    public static User fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, User.class);
    }

    public User() {

    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + getUsername() + '\'' +
                "password='" + getPassword() +
                "gender='" + getGender() + '}';
    }


    public void sendFriendRequest(User receiver) {
        if (receiver != null && !this.friendList.contains(receiver) && !hasPendingRequestsWith(receiver)) {
            FriendRequestMessage friendRequestMessage = new FriendRequestMessage(this, receiver); //muss dann geändert werden
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

        if (friendRequest.getStatus() == FriendRequest.FriendRequestStatus.ACCEPTED) {
            return acceptMsg;
        } else if (friendRequest.getStatus() == FriendRequest.FriendRequestStatus.DECLINED) {
            return declineMsg;
        } else {
            return pendingMsg;
        }
    }

    public static boolean acceptFriendRequest(String sender, String receiver) {
        User senderUser = UserService.registeredUsers.get(sender);
        User receiverUser = UserService.registeredUsers.get(receiver);
        System.out.println(".." + sender);
        System.out.println(".." + receiver);

        if(senderUser == null || receiverUser == null) {
            return false;
        }
        //check if status is pending AND current user is the actual receiver of this friendRequest
            senderUser.friendList.add(receiverUser);
            receiverUser.friendList.add(senderUser);

            PrivateChat chatRoom = new PrivateChat(senderUser.username, receiverUser.username);
            UserService.privateChats.put(chatRoom.getChatId(), chatRoom); //adding privatchatroom to UserService HashMap
            senderUser.getPrivateChats().add(chatRoom);
            receiverUser.getPrivateChats().add(chatRoom);
            return true;
    }

    public boolean sendMessage(String senderUsername, int chatId, String messageText) {
        User sender = UserService.findUser(senderUsername);
        PrivateChat chat = UserService.findPrivatChatById(chatId);
        PrivateChatMessage newMessage = new PrivateChatMessage(messageText, senderUsername, chatId);
        chat.getMessages().add(newMessage);
        System.out.println("Message successfully sent to " + chatId);
        return true;
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
        if (this.friendList.contains(user)) {
            this.friendList.remove(user);
            user.friendList.remove(this);

            //Removing their chatroom
            PrivateChat chatToRemove = new PrivateChat(this.username, user.username);
            this.privateChats.remove(chatToRemove);
            user.privateChats.remove(chatToRemove);
            this.privateChats.remove(chatToRemove);
            user.privateChats.remove(chatToRemove);
        }
    }


    public void addChatToUser(PrivateChat chat) {
        this.privateChats.add(chat);
    }


}
